package statement

import expression.Expression
import expression.Impl
import statement.ProofBy.*


enum class ProofBy(val annotation: String) {
    AXIOM("Ax. sch."), HYPOTHESIS("Hypothesis"), MODUSPONENS("M.P.");
}

open class Proof : Statement {
    var indexInList: Int

    constructor(str: String, indexInList: Int) : super(str) {
        this.indexInList = indexInList
    }

    constructor(expr: Expression, indexInList: Int) : super(expr) {
        this.indexInList = indexInList
    }

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
                val first = mapProofToIndex.getValue(el.left)
                val second = mapProofToIndex.getValue(el)
                if (first < indexInList && second < indexInList) {
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

    fun getAsString(mapToNewIndices: HashMap<Int, Int>, ind: Int): String {
        mapToNewIndices[indexInList] = ind
        val annotation = when (proofBy) {
            AXIOM -> AXIOM.annotation + " " + (proofByIndex.first + 1)
            HYPOTHESIS -> HYPOTHESIS.annotation + " " + (proofByIndex.first + 1)
            MODUSPONENS -> MODUSPONENS.annotation + " " +
                    mapToNewIndices[proofByIndex.second] + ", " + mapToNewIndices[proofByIndex.first]
            null -> throw NullPointerException()
        }
        return "[$ind. $annotation] ${expr.toStr()}"
    }
}