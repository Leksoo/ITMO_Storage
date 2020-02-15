package generator

import grammar.*

class ParserGenerator(
    private val out: String,
    private val grammar: Grammar
) {


    private val FIRST = constructFIRST()
    private val FOLLOW = constructFOLLOW()
    private val startRuleBlock = grammar.rulesBlocks.find { it.name == grammar.startNonTerm }!!


    fun generate() {
        if (startRuleBlock.returnAttrs == null) {
            throw GeneratorException("you starting rule returns nothing")
        }
        Writer("${out}Parser.kt").use { w ->
            w.println(grammar.headerCode.trim())
            w.println(
                """
                import generator.END_INPUT_ERROR
                import generator.ParserException
                import generator.parserError
                import java.io.FileReader
                import java.util.regex.Pattern
                import kotlin.collections.HashMap
            """.trimIndent()
            )
            w.println(
                """
                class Parser(
                    private val lexer : Lexer
                ){
                    private lateinit var curToken : Token
                    private lateinit var curValue : String
                    
                    private fun next() : Token {
                       lexer.next().let { 
                          curToken=it.second
                          curValue=it.first
                       }
                        return curToken
                    }
                    
                    fun parse() : ${startRuleBlock.returnAttrs.type}{
                        next()
                        val ${startRuleBlock.returnAttrs.name} = ${makeFuncName(startRuleBlock.name)}()
                        if(curToken != Token.END){
                            parserError(END_INPUT_ERROR + curValue)
                        }
                        return ${startRuleBlock.returnAttrs.name}
                    }
                
            """.trimIndent()
            )

            for ((ruleBlockInd, ruleBlock) in grammar.rulesBlocks.withIndex()) {
                w.println(
                    1,
                    "${makeParseFuncDeclaration(ruleBlock.name, ruleBlock.returnAttrs, ruleBlock.inheritedAttrs)} {"
                )
                w.println(2, ruleBlock.returnAttrs?.let { "var ${it.name} : ${it.type}? = null" } ?: "")
                w.println(2,"when(curToken){")
                ruleBlock.rules.forEach { w.println(3,processFirst(it)) }
                if(ruleBlock.rules.any{ it.first.contains("EPS")}){
                    val curFollow = FOLLOW[ruleBlock.name]!!
                    w.println(3, "${curFollow.joinToString(", ") { "Token.$it" }} -> {\n")
                    w.println(4, processRule(ruleBlock.rules.find { it.epsRule == true}!!))
                    w.println(3,"}")
                }
                w.println(3,"else ->{parserError(\"unexpected token: ${"$"}curValue\")}")
                w.println(2, "}")
                ruleBlock.returnAttrs?.let {
                    w.println(2,"return ${it.name}!!")
                }
                w.println(1, "}")
            }

            w.print("}")
        }

    }

    private fun processFirst(rule: Rule): String {
        val a= ("${rule.first.filter { it != "EPS" }.apply { if (isEmpty()) return "" }.joinToString(", ") { "Token.$it" }} -> {\n" +
                 processRule(rule)+
                "\n}").split("\n").joinToString("\n"+TAB.repeat(3))
        return a
    }

    private fun processRule(rule : Rule) : String {
        val res = StringBuilder("\n")
        for (el in rule.parts){
            if( el is Term){
                if(el.name != "EPS") {
                    res.appendln("val ${el.name} = curValue")
                    res.appendln("if(curToken.name != \"${el.name}\") parserError(\"unexpected token: ${"$"}curValue\")" )
                    res.appendln("next()")
                }
                res.appendln(el.code ?: "")
            }
            else if(el is NonTerm){
                res.appendln("val ${el.name} = ${makeFuncName(el.name)}${el.attr?.let { "(${it.name})"} ?: "()"}")
                res.appendln(el.code ?: "")
            }
        }
        return res.toString().split("\n").joinToString("\n"+TAB.repeat(1))
    }



    private fun makeFuncName(nonTerm: String) = "parse_$nonTerm"

    private fun makeParseFuncDeclaration(nonTerm: String, returnAttr: Attr?, inheritAttr: Attr?): String {
        return "private fun ${makeFuncName(nonTerm)}(${inheritAttr?.let { "${it.name} : ${it.type}" }
            ?: ""}) : ${returnAttr?.type
            ?: "Unit"}"
    }


    private fun constructFIRST(): HashMap<String, HashSet<String>> {
        val res = HashMap<String, HashSet<String>>()
        var changed = true
        grammar.rulesBlocks.forEach {
            res[it.name] = HashSet()
        }
        while (changed) {
            changed = false
            grammar.rulesBlocks.forEach { ruleBlock ->
                ruleBlock.rules.forEach { rule ->
                    val firstInRule = rule.parts[0]
                    if (firstInRule is Term) {
                        changed = changed or res[ruleBlock.name]!!.add(firstInRule.name)
                        rule.first.add(firstInRule.name)
                    } else if (firstInRule is NonTerm) {
                        changed = changed or res[ruleBlock.name]!!.addAll(res[firstInRule.name]!!)
                        rule.first.addAll(res[firstInRule.name]!!)
                    }
                }
            }
        }

        return res
    }

    private fun constructFOLLOW(): HashMap<String, HashSet<String>> {
        val res = HashMap<String, HashSet<String>>()
        grammar.rulesBlocks.forEach {
            res[it.name] = HashSet()
        }
        res[grammar.startNonTerm]!!.add("END")
        var changed = true
        while (changed) {
            changed = false
            grammar.rulesBlocks.forEach { ruleBlock ->
                ruleBlock.rules.forEach { rule ->
                    for ((ind, el) in rule.parts.withIndex()) {
                        if (el is NonTerm) {
                            if (ind != rule.parts.size - 1) {
                                val nextEl = rule.parts[ind + 1]
                                if (nextEl is Term) {
                                    changed = changed or res[el.name]!!.add(nextEl.name)
                                } else {
                                    changed =
                                        changed or res[el.name]!!.addAll(FIRST[nextEl.name]!!.filter { it != "EPS" })
                                    if (FIRST[nextEl.name]!!.contains("EPS")) {
                                        changed = changed or res[el.name]!!.addAll(res[ruleBlock.name]!!)
                                    }
                                }
                            } else {
                                changed = changed or res[el.name]!!.addAll(res[ruleBlock.name]!!)

                            }
                        }
                    }
                }
            }
        }
        return res

    }


    private fun printOut(c: HashMap<String, HashSet<String>>) {
        c.forEach {
            print("${it.key} : ")
            println(it.value.joinToString(", "))
        }
        print("\n\n")
    }

    fun printFirst() = printOut(FIRST)
    fun printFOLLOW() = printOut(FOLLOW)
    val TAB = "    "
}