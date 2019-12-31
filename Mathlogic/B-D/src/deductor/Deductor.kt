package deductor

import AtoA
import expression.Expression
import expression.Impl
import expression.Not
import expression.Variable
import getAsExpressionList
import processor.ValidationProcessor
import statement.Axiom
import statement.ProofBy
import statement.ProofsBlock

class Deductor(
    val axiomList : ArrayList<Axiom>,
    val target : Expression,
    val hypothesises : List<Expression>,
    val hypothesisToExclude: Expression,
    val exprList : List<Expression>
) {
    val forAxAndHyp = listOf(
        Impl(Variable("A"),Impl(Variable("B"),Variable("A"))),
        Variable("A")
    )
    val forHypEqualsProof = getAsExpressionList(AtoA).reversed()

    val forModusPonens = getAsExpressionList(listOf(
        "(A->(B->C))->(A->C)",
        "(A-> B)->(A->(B->C))->(A->C)"
    ))


    fun deduct() : ProofsBlock{
        if( hypothesises.isEmpty()){
            return ProofsBlock(exprList,0)
        }
        val validator = ValidationProcessor(axiomList,hypothesises,exprList,target)
        validator.validateWithExprs()
        val proofs = validator.getNewProofList()

        val result = ArrayList<Expression>()
        for (proof in proofs.reversed()){
            if(proof.expr== Not(Variable("B"))){
                print("")
            }
            val proofExpr = proof.expr
            result.add(Impl(hypothesisToExclude,proofExpr))
            if(proofExpr == hypothesisToExclude){
                val letterToExpr = HashMap<String,Expression>()
                letterToExpr["A"] = proofExpr
                result.addAll(ProofsBlock(forHypEqualsProof,0,letterToExpr).proofList)
            }
            else if(proof.proofBy == ProofBy.AXIOM || proof.proofBy == ProofBy.HYPOTHESIS){
                val letterToExpr = HashMap<String,Expression>()
                letterToExpr["A"] = proofExpr
                letterToExpr["B"] = hypothesisToExclude
                result.addAll(ProofsBlock(forAxAndHyp,0,letterToExpr).proofList)
            }

            else if(proof.proofBy == ProofBy.MODUSPONENS){
                val j = proofs[proof.proofByIndex.first]
                val jToi = proofs[proof.proofByIndex.second]
                val letterToExpr = HashMap<String,Expression>()
                letterToExpr["A"] = hypothesisToExclude
                letterToExpr["B"] = j.expr
                letterToExpr["C"] = (jToi.expr as Impl).right
                result.addAll(ProofsBlock(forModusPonens,0,letterToExpr).proofList)

            }

        }
        return ProofsBlock(ArrayList(result.reversed()),0)
    }
}