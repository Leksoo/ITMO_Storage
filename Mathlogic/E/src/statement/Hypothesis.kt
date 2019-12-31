package statement


class Hypothesis(
    str: String,
    ind: Int

) : TrueStatement(str, ind) {
    override fun canSetInProof(proof: Proof): Boolean {
        if (expr == proof.expr) {
            return true
        }
        return false
    }

    override fun setParamsToProof(proof: Proof) {
        proof.setProofParams(ProofBy.HYPOTHESIS, indexInList)

    }

}