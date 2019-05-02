package processor

import deductor.Deductor
import expelThird
import expression.*
import getAsExpressionList
import parsers.Parser
import statement.Axiom
import statement.ProofsBlock
import statement.Statement
import statement.TrueStatement
import java.io.BufferedOutputStream
import java.util.stream.Collectors

class FullnessProcessor(
    val axiomList: ArrayList<Axiom>,
    val statement: String
) {
    val dfsStartsString: ArrayList<ArrayList<String>> = arrayListOf(

    )
    val expelThirdProof = getAsExpressionList(expelThird)
    val expelHypProof = getAsExpressionList(
        listOf(
            "(A->B) ->(!A -> B) -> (A | !A) -> B",
            "(!A -> B) -> (A | !A) -> B",
            "(A | !A) -> B",
            "B"
        )
    )
    val dfsStarts = ArrayList<List<Expression>>()
    val genCheck = GenCheck()
    var whatToProve: Statement
        private set

    init {
        whatToProve = TrueStatement(statement, -1)

        //find different variables
        val vars = HashSet<String>()
        getVars(whatToProve.expr, vars)
        val varList = ArrayList(vars)
        if (vars.size == 1) {
            for (i in 0 until 2) {
                var el0 = varList[0]
                if (i == 0) {
                    el0 = "!" + el0
                }
                dfsStartsString.add(arrayListOf(el0))
            }
        } else if (vars.size == 3) {
            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    for (k in 0 until 2) {
                        var el0 = varList[0]
                        if (i == 0) {
                            el0 = "!" + el0
                        }
                        var el1 = varList[1]
                        if (j == 0) {
                            el1 = "!" + el1
                        }
                        var el2 = varList[2]
                        if (k == 0) {
                            el2 = "!" + el2
                        }
                        dfsStartsString.add(arrayListOf(el0, el1, el2))
                    }
                }
            }
        } else if (vars.size == 2) {
            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    var el0 = varList[0]
                    if (i == 0) {
                        el0 = "!" + el0
                    }
                    var el1 = varList[1]
                    if (j == 0) {
                        el1 = "!" + el1
                    }
                    dfsStartsString.add(arrayListOf(el0, el1))
                }
            }
        }
        dfsStartsString.forEach {
            dfsStarts.add(it.stream().map { Parser.parse(it) }.collect(Collectors.toList()))
        }


    }

    private fun getVars(expr: Expression, vars: HashSet<String>) {
        when (expr) {
            is BinaryOperator -> {
                getVars(expr.left, vars)
                getVars(expr.right, vars)
            }
            is UnaryOperator -> getVars(expr.right, vars)
            is Variable -> vars.add(expr.str)
        }
    }

    fun generate(out: BufferedOutputStream) {
        if (generateTarget(out,whatToProve.expr)) {
            return
        } else if (generateNotTarget(out,Not(whatToProve.expr))) {
            return
        }
        out.write(":(".toByteArray())
    }

    private fun generateTarget(out: BufferedOutputStream,target: Expression): Boolean {
        val possibleHyps = generate(target)
        var ind = -1
        var minSize = 10
        for ((i, hyp) in possibleHyps.withIndex()) {
            val el = hyp.first
            if ((el.all { it !is Not } && el.size < minSize) || el.isEmpty()) {
                ind = i
                minSize = el.size
            }
        }
        if (minSize != 10) {
            val hyps = possibleHyps[ind].first
            //debug
//            print("proof target, our choice : ")
//            hyps.forEach { print("${it.toStr()} ") }
//            println()
            //
            generateProof(hyps, possibleHyps[ind].second, out,target)
            return true
        }
        return false
    }

    private fun generateNotTarget(out: BufferedOutputStream, target: Expression): Boolean {
        val possibleHyps = generate(target)
        var ind = -1
        var minSize = 10
        for ((i, hyp) in possibleHyps.withIndex()) {
            val el = hyp.first
            if ((el.all { it is Not } && el.size < minSize) || el.isEmpty()) {
                ind = i
                minSize = el.size
            }
        }
        if (minSize != 10 || possibleHyps.isEmpty()) {
            val hyps = possibleHyps[ind].first
            //debug
//            print("proof target, our choice : ")
//            hyps.forEach { print("${it.toStr()} ") }
//            println()
            //
            generateProof(hyps, possibleHyps[ind].second, out,target)
            return true
        }
        return false
    }

    private fun generateProof(hyps: List<Expression>, path: Node, out: BufferedOutputStream,target: Expression) {
        val res = walkTreeProof(path)
        val statement = hyps.joinToString(separator = ", ") { it.toStr() } +
                (if (hyps.isEmpty()) "" else " ") + "|- " + target.toStr()
        out.write(statement.toByteArray())
        out.write("\n".toByteArray())
        for (block in res) {
            out.write(block.toString().toByteArray())
            out.write("\n".toByteArray())
        }
    }

    private fun walkTreeProof(path: Node): ArrayList<ProofsBlock> {
        val res = ArrayList<ProofsBlock>()
        // expel third
        if (path.expelled) {

            if (path.left != null && path.left!!.hyps != null) {
                val proofLeft = walkTreeProof(path.left!!)
                val deductor = Deductor(
                    axiomList, path.expr,
                    path.left!!.hyps!!, path.left!!.hyps!![path.expelledVarIndex],
                    proofLeft.stream().map { it.proofList }.flatMap { it.stream() }.collect(Collectors.toList())
                )
                val ded = deductor.deduct()
                res.add(ded)
                //debug
//                println("----------------------------")
//                println("left side deduct")
//                for (el in path.left!!.hyps!!){
//                    print("${el.toStr()} ")
//                }
//                println()
//                println("to expel : ${path.left!!.hyps!![path.expelledVarIndex].toStr()}")
//                for (proof in ded.proofList) {
//                    println(proof.toStr())
//                }
//                println("----------------------------")
                //
            }
            if (path.right != null) {
                val proofRight = walkTreeProof(path.right!!)
                val deductor = Deductor(
                    axiomList, path.expr,
                    path.right!!.hyps!!, path.right!!.hyps!![path.expelledVarIndex],
                    proofRight.stream().map { it.proofList }.flatMap { it.stream() }.collect(Collectors.toList())
                )
                val ded = deductor.deduct()
                res.add(ded)
                //debug
//                println("----------------------------")
//                println("right side deduct")
//                for (el in path.right!!.hyps!!){
//                    print("${el.toStr()} ")
//                }
//                println()
//                println("to expel : ${path.right!!.hyps!![path.expelledVarIndex].toStr()}")
//                for (proof in ded.proofList) {
//                    println(proof.toStr())
//                }
//                println("----------------------------")
                //
            }
            val letterToExpr = HashMap<String, Expression>()
            if (path.right!!.hyps!![path.expelledVarIndex] !is Not) {
                letterToExpr["A"] = path.right!!.hyps!![path.expelledVarIndex]

            } else {
                letterToExpr["A"] = path.left!!.hyps!![path.expelledVarIndex]
            }
            val expelTh = ProofsBlock(expelThirdProof, 0, letterToExpr)
            res.add(expelTh)
            //debug
//            println("----------------------------")
//            println("expel third")
//            for (proof in expelTh.proofList) {
//                println(proof.toStr())
//            }
//            println("----------------------------")
            //
            letterToExpr["B"] = path.expr
            val expelPr = ProofsBlock(expelHypProof, 0, letterToExpr)
            res.add(expelPr)
            //debug
//            println("----------------------------")
//            println("ending merge")
//            for (proof in expelPr.proofList) {
//                println(proof.toStr())
//            }
//            println("----------------------------")
            //

        } else {
            // by fullness axioms
            if (path.hyps == null || (!path.expelled)) {
                var proofLeft = ArrayList<ProofsBlock>()
                var proofRight = ArrayList<ProofsBlock>()
                val letterToExpr = HashMap<String, Expression>()
                if (path.left != null) {
                    proofLeft = walkTreeProof(path.left!!)


                }
                if (path.right != null) {
                    proofRight = walkTreeProof(path.right!!)

                }
                res.addAll(proofLeft)
                res.addAll(proofRight)
                if(path.placeIn.first!=null) {
                    letterToExpr["A"] = path.placeIn.first!!
                }
                if(path.placeIn.second!=null) {
                    letterToExpr["B"] = path.placeIn.second!!
                }

                if (path.proof != null) {
                    val r = ProofsBlock(path.proof!!, -1, letterToExpr)
                    res.add(r)
                }
                //debug
//                println("$$$$$$$$ -by fullness")
//                for (el in proofLeft) {
//                    for (proof in el.proofList) {
//                        println(proof.toStr())
//                    }
//                }
//                println("----------------------------")
//                for (el in proofRight) {
//                    for (proof in el.proofList) {
//                        println(proof.toStr())
//                    }
//                }
//                println("----------------------------")
//                if(path.proof!=null) {
//                    val r = ProofsBlock(path.proof!!, -1, letterToExpr)
//
//                    for (proof in r.proofList) {
//                        println(proof.toStr())
//                    }
//                }
//                println("----------------------------")


            }

        }
        return res
    }


    private fun generate(toProof: Expression): ArrayList<Pair<List<Expression>, Node>> {
        var possibleHypGroups = ArrayList<Pair<List<Expression>, Node>>()
        for (start in dfsStarts) {
            val target = toProof
            val path = Node(target, start, false)

            if (walkTreeForPossibility(target, start, path)) {
                possibleHypGroups.add(Pair(start, path))
            }

        }

        while (true) {
            //debug
//            for (el in possibleHypGroups) {
//                el.first.forEach { print("${it.toStr()} ") }
//                println()
//            }
//            println("----------------------------")
            //
            val curSize = possibleHypGroups.size
            var stop = true
            val toExpelIndices = HashSet<Int>()
            var newHypGroups = ArrayList<Pair<List<Expression>, Node>>()
            for (i in 0 until curSize) {
                for (j in i + 1 until curSize) {
                    val f = possibleHypGroups[i].first
                    val s = possibleHypGroups[j].first
                    val diff = checkForExpel(f, s)
                    if (diff != -1) {
                        val newF = f.filterIndexed { index, _ -> index != diff }.toList()
                        toExpelIndices.add(i)
                        toExpelIndices.add(j)
                        val newNode = Node(toProof, newF, true)
                        newNode.expelledVarIndex = diff
                        newNode.left = possibleHypGroups[i].second
                        newNode.right = possibleHypGroups[j].second
                        newNode.proof = listOf()
                        newHypGroups.add(Pair(newF, newNode))
                        stop = false
                        break
                    }
                }
            }

            if (stop) {
                break
            } else {
                newHypGroups.addAll(possibleHypGroups.filterIndexed { index, _ ->
                    !toExpelIndices.contains(index)
                }.toList())
                possibleHypGroups = newHypGroups
            }

        }
        //debug
//        for (el in possibleHypGroups) {
//            el.first.forEach { print("${it.toStr()} ") }
//            println()
//        }
//        println("----------------------------")
        //

        return possibleHypGroups
    }

    private fun checkForExpel(f: List<Expression>, s: List<Expression>): Int {
        if (f.size != s.size) {
            return -1
        }
        var diffPos = -1
        var diffCount = 0
        for (i in 0 until f.size) {
            if (Not(s[i]) == f[i] || Not(f[i]) == s[i]) {
                diffPos = i
                diffCount++
            } else if (s[i] != f[i]) {
                diffCount = 0
                break
            }
        }
        if (diffCount == 1) {
            return diffPos
        }
        return -1
    }


    private fun walkTreeForPossibility(
        target: Expression,
        variables: List<Expression>,
        path: Node
    ): Boolean {
        val children = genCheck.getExpressions(target)
        var res = false
        if (children.isEmpty()) {
            path.placeIn = Pair(null,null)
            if (variables.contains(target)) {
                path.proof = listOf(target)
                res = true
            }
        } else {
            for (option in children) {
                val proof = option.proofTemplate
                var possibleToGen = true
                var node1: Node? = null
                var node2: Node? = null
                var can1 = true
                var can2 = true
                if (option.left.exprOfTree != null) {
                    node1 = Node(option.left.exprOfTree, null, false)
                    can1 = walkTreeForPossibility(option.left.exprOfTree, variables, node1)
                }

                if (option.right.exprOfTree != null) {
                    node2 = Node(option.right.exprOfTree, null, false)
                    can2 = walkTreeForPossibility(option.right.exprOfTree, variables, node2)
                }
                path.placeIn = Pair(option.left.exprToPlaceIn,option.right.exprToPlaceIn)
                possibleToGen = possibleToGen and can1 and can2
                if (can1 && can2) {
                    path.left = node1
                    path.right = node2
                }
                res = res or possibleToGen
                if (possibleToGen) {
                    path.proof = proof
                    break
                }
            }

        }
        return res
    }

    class Node(
        val expr: Expression,
        val hyps: List<Expression>?,
        val expelled: Boolean
    ) {
        var expelledVarIndex: Int = 0
        var proof: List<Expression>? = null
        var left: Node? = null
        var right: Node? = null
        lateinit var placeIn : Pair<Expression?,Expression?>
    }
}