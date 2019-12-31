package statement

import expression.Expression
import expression.Impl
import expression.Variable
import statement.ProofBy.*


enum class ProofBy{
    AXIOM, HYPOTHESIS, MODUSPONENS, EXISTENCE, ALL, FORMALEXPR;
}

class Proof(
    str: String,
    var indexInList: Int
) : Statement(str) {
    var usedInProof: Boolean = false
    var proofBy: ProofBy? = null

    // i-True ; i -> j ; j-True
    var proofByIndex: Pair<Int, Int> = Pair(-1, -1)

    fun setProofParams(pb: ProofBy, isTrueInd: Int, fromTrueInd: Int = -1) {
        proofBy = pb
        proofByIndex = Pair(isTrueInd, fromTrueInd)
    }


    fun canBeGotByModusPonens(
        mapProofToIndex: Map<Expression, Int>,
        mapOfRightParts: Map<Expression, Set<Expression>>
    ): Boolean {
        if (!mapOfRightParts.containsKey(this.expr)) {
            return false
        }
        val setOfHoleExprs = mapOfRightParts.getValue(this.expr)
        for (el in setOfHoleExprs) {
            if (el is Impl && mapProofToIndex.containsKey(el.left)) {
                val first =mapProofToIndex.getValue(el.left)
                val second = mapProofToIndex.getValue(el)
                if(first < indexInList && second < indexInList) {
                    setProofParams(MODUSPONENS, first, second)
                    return true
                }
            }
        }
        return false


    }


    fun markUsedProofs(proofList: List<Proof>) {
        usedInProof = true
        if (proofBy == MODUSPONENS) {
            proofList[proofByIndex.first].markUsedProofs(proofList)
            proofList[proofByIndex.second].markUsedProofs(proofList)

        }
    }

}