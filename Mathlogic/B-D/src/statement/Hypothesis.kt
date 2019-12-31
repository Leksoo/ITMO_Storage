package statement

import expression.Expression

class Hypothesis : TrueStatement {

    constructor(str: String, indexInList: Int) : super(str, indexInList)

    constructor(expr: Expression, indexInList: Int) : super(expr, indexInList)

    override fun canSetInProof(proof: Proof): Boolean {
        if (expr.equalByTree(proof.expr, HashMap(), false)) {
            return true
        }
        return false
    }

    override fun setParamsToProof(proof: Proof) {
        proof.setProofParams(ProofBy.HYPOTHESIS, indexInList)

    }

}