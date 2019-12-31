package logic

import expression.Abstr
import expression.Apply
import expression.Expression
import expression.Variable
import parse
import type.Impl
import type.Type
import type.TypeInfo
import type.TypeVar
import java.lang.StringBuilder

class Typer {
    lateinit var lambda: Expression

    fun process(input: String): String {
        lambda = parse(input)
        go(lambda, HashMap())
        val solvedSystem = lambda.typeInfo.getSolvedSystem() ?: return "Expression has no type"
        val freeVarsString = freeVars.map { Pair(it.first, solvedSystem[it.second as TypeVar] ?: it.second) }
            .joinToString(", ") { "${it.first} : ${it.second}" }
        val res = ArrayList<String>()
        makeString(lambda, 0, freeVarsString, ArrayList(), solvedSystem, res)
        return res.joinToString("\n")
    }

    private fun makeString(
        cur: Expression,
        level: Int,
        freeVarsString: String,
        chained: ArrayList<Pair<String, Type>>,
        types: Map<TypeVar, Type>,
        res: ArrayList<String>
    ) {
        val resSb = StringBuilder()
        var curLevel = level
        if(curLevel != 0) {
            while (curLevel != 0) {
                resSb.append("*   ")
                curLevel--
            }
        }
        resSb.append(freeVarsString)
        val varTypes : String
        val ind =chained.map { it.first}.indexOf(cur.toString())
        if(ind != -1){
            val chainedCopy = ArrayList(chained)
            val el = chained[ind]
            chainedCopy.apply {
                removeAt(ind)
                add(el)
            }
            varTypes = chainedCopy.joinToString(" , ") { "${it.first} : ${it.second}" }
        }else {
            varTypes = chained.joinToString(" , ") { "${it.first} : ${it.second}" }
        }
        if (freeVarsString.isNotEmpty() && varTypes.isNotEmpty()) {
            resSb.append(", ")
        }
        resSb.append(varTypes)
        if(freeVarsString.isNotEmpty() || varTypes.isNotEmpty()){
            resSb.append(" ")
        }
        resSb.append("|- ").append(cur.toString()).append(" : ")
            .append(cur.typeInfo.type.substitute(types).toString()).append(" ").append(cur.typeInfo.rule)
        res.add(resSb.toString())

        when (cur) {
            is Abstr -> {
                val newChained = ArrayList(chained)
                newChained.add(Pair(cur.str.str, types[cur.str.typeInfo.type] ?: cur.str.typeInfo.type))
                makeString(cur.right, level + 1, freeVarsString, newChained, types, res)
            }
            is Apply -> {
                makeString(cur.left, level + 1, freeVarsString, chained, types, res)
                makeString(cur.right, level + 1, freeVarsString, chained, types, res)
            }

        }

    }

    val freeVars = ArrayList<Pair<Variable, Type>>()
    var typeCounter = 0
    private fun getNewType() = "t$typeCounter".also { typeCounter++ }

    private fun go(cur: Expression, chained: Map<String, String>) {
        var newChained: Map<String, String> = HashMap()
        when (cur) {
            is Abstr -> {
                newChained = HashMap(chained)
                newChained[cur.str.str] = getNewType()
                cur.str.typeInfo = TypeInfo(TypeVar(newChained[cur.str.str]!!), ArrayList(),"")
                go(cur.right, newChained)
            }
            is Apply -> {
                go(cur.left, chained)
                go(cur.right, chained)
            }

        }
        when (cur) {
            is Abstr -> {
                val typeInfo = TypeInfo(
                    Impl(TypeVar(newChained[cur.str.str]!!), cur.right.typeInfo.type),
                    ArrayList(cur.right.typeInfo.system),
                    "[rule #3]"
                )
                cur.typeInfo = typeInfo

            }
            is Apply -> {
                val newTypeVar = TypeVar(getNewType())
                val typeInfo = TypeInfo(
                    newTypeVar,
                    ArrayList(cur.left.typeInfo.system.union(cur.right.typeInfo.system))
                        .also { it.add(Pair(cur.left.typeInfo.type, Impl(cur.right.typeInfo.type, newTypeVar))) },
                    "[rule #2]"
                )
                cur.typeInfo = typeInfo

            }
            is Variable -> {
                val typeInfo = if (chained.containsKey(cur.str)) {
                    TypeInfo(TypeVar(chained[cur.str]!!), ArrayList(), "[rule #1]")
                } else {
                    val was = freeVars.find { it.first == cur }
                    if(was!= null){
                        was.first.typeInfo
                    }
                    else {
                        val typeVar = TypeVar(getNewType())
                        freeVars.add(Pair(cur, typeVar))
                        TypeInfo(typeVar, ArrayList(), "[rule #1]")
                    }
                }
                cur.typeInfo = typeInfo
            }
        }
    }
}