package processor

import AandB
import AimplB1
import AimplB2
import AorB1
import AorB2
import NotAandB1
import NotAandB2
import NotAimplB
import NotAorB
import NotNotA
import expression.*
import getAsExpressionList
import java.lang.NullPointerException

class Option(
    val left : NodeData,
    val right : NodeData,
    val proofTemplate: List<Expression>
)

class NodeData(
    val exprOfTree: Expression?
){
     var exprToPlaceIn: Expression? = null
}

class GenCheck {
    // B |- A -> B
    val implArg1 = listOf<Expression?>(null, Variable("B"))
    val implProof1 = getAsExpressionList(AimplB1)
    // !A |- A -> B
    val implArg2 = listOf<Expression?>(Not(Variable("A")), null)
    val implProof2 = getAsExpressionList(AimplB2)
    // A, !B |- !(A -> B)
    val notimplArg1 = listOf<Expression?>(Variable("A"), Not(Variable("B")))
    val notimplProof1 = getAsExpressionList(NotAimplB)
    // A |- !!A
    val notnotArg1 = listOf<Expression?>(Variable("A"), null)
    val notnotProof1 = getAsExpressionList(NotNotA)
    // B |- A | B
    val orArg1 = listOf<Expression?>(null, Variable("B"))
    val orProof1 = getAsExpressionList(AorB1)
    // A |- A | B
    val orArg2 = listOf<Expression?>(Variable("A"), null)
    val orProof2 = getAsExpressionList(AorB2)
    // !A,!B |- !(A | B)
    val notorArg1 = listOf<Expression?>(Not(Variable("A")), Not(Variable("B")))
    val notorProof1 = getAsExpressionList(NotAorB)
    //  A,B |- A & B
    val andArg1 = listOf<Expression?>(Variable("A"), Variable("B"))
    val andProof1 = getAsExpressionList(AandB)
    // !B |- !(A & B)
    val notandArg1 = listOf<Expression?>(null, Not(Variable("B")))
    val notandProof1 = getAsExpressionList(NotAandB1)
    // !A |- !(A & B)
    val notandArg2 = listOf<Expression?>(Not(Variable("A")), null)
    val notandProof2 = getAsExpressionList(NotAandB2)


 fun placeIn(templates : List<Expression?>,letterToExpr : HashMap<String, Expression>) : Pair<NodeData,NodeData>{
     var nodeDataLeft : NodeData
     var nodeDataRight : NodeData
     nodeDataLeft = if(templates[0]!= null){
         NodeData(templates[0]!!.getNewExpr(letterToExpr))
     } else{
         NodeData(null)
     }
     nodeDataLeft.exprToPlaceIn = letterToExpr["A"]
     nodeDataRight = if(templates[1]!= null){
         NodeData(templates[1]!!.getNewExpr(letterToExpr))
     } else{
         NodeData(null)
     }
     nodeDataRight.exprToPlaceIn = letterToExpr["B"]

     return Pair(nodeDataLeft,nodeDataRight)
 }



    fun getExpressions(target: Expression):
            List<Option> {
        val letterToExpr = HashMap<String, Expression>()
        val res = ArrayList<Option>()
        if (target is Not && target.right is Not) {
            letterToExpr["A"] = target.right.right
            val nodes = placeIn(notnotArg1,letterToExpr)
            val opt = Option(nodes.first,nodes.second,notnotProof1)
            res.add(opt)
        } else if (target is Not && target.right is BinaryOperator) {
            letterToExpr["A"] = target.right.left
            letterToExpr["B"] = target.right.right

            when (target.right) {
                is Impl -> {
                    var nodes1 = placeIn(notimplArg1,letterToExpr)
                    val opt1 = Option(nodes1.first,nodes1.second,notimplProof1)
                    res.add(opt1)
                }
                is And -> {
                    var nodes1 = placeIn(notandArg1,letterToExpr)
                    val opt1 = Option(nodes1.first,nodes1.second,notandProof1)
                    var nodes2 = placeIn(notandArg2,letterToExpr)
                    val opt2 = Option(nodes2.first,nodes2.second,notandProof2)
                    res.add(opt1)
                    res.add(opt2)
                }
                is Or -> {
                    var nodes1 = placeIn(notorArg1,letterToExpr)
                    val opt1 = Option(nodes1.first,nodes1.second,notorProof1)
                    res.add(opt1)
                }
            }
        }
        if (target is BinaryOperator) {
            letterToExpr["A"] = target.left
            letterToExpr["B"] = target.right
            when (target) {
                is Impl -> {
                    var nodes1 = placeIn(implArg1,letterToExpr)
                    val opt1 = Option(nodes1.first,nodes1.second,implProof1)
                    var nodes2 = placeIn(implArg2,letterToExpr)
                    val opt2 = Option(nodes2.first,nodes2.second,implProof2)
                    res.add(opt1)
                    res.add(opt2)
                }
                is And -> {
                    var nodes1 = placeIn(andArg1,letterToExpr)
                    val opt1 = Option(nodes1.first,nodes1.second,andProof1)
                    res.add(opt1)

                }
                is Or -> {
                    var nodes1 = placeIn(orArg1,letterToExpr)
                    val opt1 = Option(nodes1.first,nodes1.second,orProof1)
                    var nodes2 = placeIn(orArg2,letterToExpr)
                    val opt2 = Option(nodes2.first,nodes2.second,orProof2)
                    res.add(opt1)
                    res.add(opt2)
                }

            }
        }
        return res
    }
}