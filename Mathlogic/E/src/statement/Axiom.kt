package statement


class Axiom(
    str: String,
    ind: Int
) : TrueStatement(str, ind) {
    override fun canSetInProof(proof: Proof): Boolean {
        if (expr.equalByTree(proof.expr, HashMap())) {
            return true
        }
        return false
    }

    override fun setParamsToProof(proof: Proof) {
        proof.setProofParams(ProofBy.AXIOM, indexInList)

    }


}