package processor

import exceptions.ParserException
import exceptions.ValidationProofException
import expression.Expression
import expression.Impl
import statement.*
import java.io.BufferedOutputStream


const val INCORRECT_PROOF_MESSAGE = "Proof is incorrect"
const val PARSER_EXCEPTION_MESSAGE = "Error: could not parse"


class ValidationProcessor {
    private  var axiomList: ArrayList<Axiom>
    private val hypothesisSet = HashSet<Hypothesis>()
    private val hypothesisList = ArrayList<Hypothesis>()
    private val proofList = ArrayList<Proof>()
    private lateinit var proofStrings: List<String>
    private lateinit var proofExprs: List<Expression>
    private val mapProofToInd = HashMap<Expression, Int>()
    private val mapProofRightPartToIndAndHoleExpr = HashMap<Expression, HashSet<Expression>>()
     var whatToProve: Statement
        private set
    private val doMinimization : Boolean

    @Throws(ParserException::class)
    constructor(
        axioms: ArrayList<Axiom>,
        hypothesises: List<String>,
        proofLines: List<String>,
        statement: String,
        doMin : Boolean
    )  {
        if (proofLines.isEmpty()) throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        doMinimization = doMin
        whatToProve = TrueStatement(statement, -1)
        if (whatToProve.expr != TrueStatement(proofLines.last(), -1).expr) {
            throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        }
        axiomList = axioms

        for ((ind, hyp) in hypothesises.withIndex()) {
            val hypothesis = Hypothesis(hyp, ind)
            hypothesisList.add(hypothesis)
            hypothesisSet.add(hypothesis)
        }

        proofStrings = proofLines
    }

    @Throws(ParserException::class)
    constructor(
        axioms: ArrayList<Axiom>,
        hypothesises: List<Expression>,
        proofExprs: List<Expression>,
        statement: Expression
    )  {
        if (proofExprs.isEmpty()) throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        doMinimization = false
        whatToProve = TrueStatement(statement, -1)
        if (whatToProve.expr != TrueStatement(proofExprs.last(), -1).expr) {
            throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        }
        axiomList = axioms

        for ((ind, hyp) in hypothesises.withIndex()) {
            val hypothesis = Hypothesis(hyp, ind)
            hypothesisList.add(hypothesis)
            hypothesisSet.add(hypothesis)
        }

        this.proofExprs = proofExprs
    }

    fun validate() {
        if (!checkProofs()) {
            throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        }
    }
    fun validateWithExprs() {
        if (!checkProofsWithExprs()) {
            throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        }
    }

    fun getNewProofList(): List<Proof> {
        val newProofs = ArrayList<Proof>()
        var i = 0
        val mapToNewIndices = HashMap<Int, Int>()
        for (proof in proofList) {
            if ( proof.usedInProof || !doMinimization) {
                mapToNewIndices[proof.indexInList] = i
                proof.indexInList = i
                    if (proof.proofBy == ProofBy.MODUSPONENS) {
                    proof.proofByIndex = Pair(
                        mapToNewIndices[proof.proofByIndex.first]!!,
                        mapToNewIndices[proof.proofByIndex.second]!!
                    )
                }
                newProofs.add(proof)
                i++
            }
        }
        return newProofs
    }

    fun getNewProofListInListSyntax()
            = "listOf(${getNewProofList().joinToString(",\n") { "\"$it\"" }})"

    fun printProofsWithAnnotations(out: BufferedOutputStream) {
        val statement = hypothesisList.joinToString(separator = ", ") { it.expr.toStr() } +
                (if (hypothesisList.isEmpty()) "" else " ") + "|- " + whatToProve.expr.toStr()
        out.write((statement).toByteArray())
        out.write("\n".toByteArray())
        var i = 1
        val mapToNewIndices = HashMap<Int, Int>()
        for (proof in proofList) {
            if (proof.usedInProof || !doMinimization ) {
                out.write(proof.getAsString(mapToNewIndices, i).toByteArray())
                out.write("\n".toByteArray())
                i++
            }
        }
    }

    private fun checkProofs(): Boolean {
        var i = 0
        val last = proofStrings.size - 1
        for ((j, value) in proofStrings.withIndex()) {
            val proof = Proof(value, i)

            if (doMinimization && j != last && mapProofToInd.containsKey(proof.expr)) {
                continue
            }
            proofList.add(proof)
            val expr = proof.expr
            mapProofToInd.putIfAbsent(expr, i)
            if (expr is Impl) {
                mapProofRightPartToIndAndHoleExpr.putIfAbsent(expr.right, HashSet())
                mapProofRightPartToIndAndHoleExpr[expr.right]!!.add(expr)
            }
            if (!matchWithTrueStatements(axiomList, proof) &&
                !matchWithTrueStatements(hypothesisSet, proof) &&
                !matchByModusPonens(proof)
            ) {
                println("line $j, $value - incorrect")
                return false
            }
            i++
        }
        proofList.last().markUsedProofs(proofList)
        return true
    }

    private fun checkProofsWithExprs(): Boolean {
        var i = 0
        val last = proofExprs.size - 1
        for ((j, value) in proofExprs.withIndex()) {
            val proof = Proof(value, i)

            if (doMinimization && j != last && mapProofToInd.containsKey(proof.expr)) {
                continue
            }
            proofList.add(proof)
            val expr = proof.expr
            mapProofToInd.putIfAbsent(expr, i)
            if (expr is Impl) {
                mapProofRightPartToIndAndHoleExpr.putIfAbsent(expr.right, HashSet())
                mapProofRightPartToIndAndHoleExpr[expr.right]!!.add(expr)
            }
            if (!matchWithTrueStatements(axiomList, proof) &&
                !matchWithTrueStatements(hypothesisSet, proof) &&
                !matchByModusPonens(proof)
            ) {
                println("line $j, ${value.toStr()} - incorrect")
                return false
            }
            i++
        }
        proofList.last().markUsedProofs(proofList)
        return true
    }


    private fun matchWithTrueStatements(collection: Collection<TrueStatement>, proof: Proof): Boolean {
        for (it in collection) {
            if (it.canSetInProof(proof)) {
                it.setParamsToProof(proof)
                return true
            }
        }
        return false
    }


    private fun matchByModusPonens(proof: Proof): Boolean {
        if (proof.canBeGotByModusPonens(mapProofToInd, mapProofRightPartToIndAndHoleExpr)) {
            return true
        }
        return false
    }


}
