package processor

import expression.*
import statement.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet


const val REQUIRED_NOT_PROVEN = "Required hasn’t been proven"
const val PARSER_EXCEPTION_MESSAGE = "Error: could not parse"
const val CORRECT_PROOF = "Proof is correct"


class ValidationProcessor() {
    private val axiomList = ArrayList<Axiom>()
    private val hypothesisSet = HashSet<Hypothesis>()
    private val implSet = HashSet<Impl>()
    private val proofList = ArrayList<Proof>()
    private lateinit var proofStrings: List<String>
    private val mapProofToInd = HashMap<Expression, Int>()
    private val mapProofRightPartToIndAndHoleExpr = HashMap<Expression, HashSet<Expression>>()
    lateinit var whatToProve: Statement
        private set

    fun proofError(line: Int): String {
        return "Line #${line + 1} can’t be obtained"
    }


    fun validate(
        axioms: List<String>,
        hypothesises: List<String>,
        proofLines: ArrayList<String>,
        statement: String
    ): String {
        if (proofLines.isEmpty()) return REQUIRED_NOT_PROVEN
        whatToProve = TrueStatement(statement, -1)
        if (whatToProve.expr != TrueStatement(proofLines.last(), -1).expr) {
            return REQUIRED_NOT_PROVEN
        }

        for ((ind, ax) in axioms.withIndex()) {
            axiomList.add(Axiom(ax, ind))
        }
        for ((ind, hyp) in hypothesises.withIndex()) {
            val hypothesis = Hypothesis(hyp, ind)
            hypothesisSet.add(hypothesis)
        }

        proofStrings = proofLines
        checkProofs().let {
            return if (it != -1) {
                proofError(it)
            } else {
                CORRECT_PROOF
            }
        }

    }


    private fun checkProofs(): Int {
        var i = 0
        val last = proofStrings.size - 1
        for ((j, value) in proofStrings.withIndex()) {
            val proof = Proof(value, i)
            if (j != last && mapProofToInd.containsKey(proof.expr)) {
                continue
            }
            proofList.add(proof)
            val expr = proof.expr
            mapProofToInd.putIfAbsent(expr, i)
            if (expr is Impl) {
                implSet.add(expr)
                mapProofRightPartToIndAndHoleExpr.putIfAbsent(expr.right, HashSet())
                mapProofRightPartToIndAndHoleExpr[expr.right]!!.add(expr)
            }
            if (!matchWithTrueStatements(axiomList, proof) &&
                !matchWithTrueStatements(hypothesisSet, proof) &&
                !matchByModusPonens(proof) &&
                !checkPredicateAxioms(proof) &&
                !checkPredicateRules(proof)
            ) {
                return j
            }
            i++
        }
        proofList.last().markUsedProofs(proofList)
        return -1
    }

    private fun checkPredicateRules(proof: Proof): Boolean {
        return checkAllRule(proof) || checkExistRule(proof)
    }

    private fun checkAllRule(proof: Proof): Boolean {
        val impl = proof.expr
        if (impl is Impl) {
            if (impl.right is All) {
                if (implSet.contains(Impl(impl.left, (impl.right as All).right))) {
                    val variable = (impl.right as All).left
                    if(!impl.left.allVars().contains(variable)){
                        return true
                    }
                    return !impl.left.checkFreedom(variable, HashSet())
                }
            }
        }
        return false
    }

    private fun checkExistRule(proof: Proof): Boolean {
        val impl = proof.expr
        if (impl is Impl) {
            if (impl.left is Exist) {
                if (implSet.contains(Impl((impl.left as Exist).right, impl.right))) {
                    val variable = (impl.left as Exist).left
                    if(!impl.right.allVars().contains(variable)){
                        return true
                    }
                    return !impl.right.checkFreedom(variable, HashSet())

                }
            }
        }
        return false
    }

    private fun checkPredicateAxioms(proof: Proof): Boolean {
        return checkPredicateAxiomAll(proof) ||
                checkPredicateAxiomExist(proof) ||
                check9thArithmeticAxiom(proof)
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

    private fun checkPredicateAxiomAll(proof: Proof): Boolean {
        val impl = proof.expr
        if (impl is Impl) {
            if (impl.left is All) {
                val variable = (impl.left as All).left
                return checkSubstitution((impl.left as All).right, impl.right, variable)
            }
        }
        return false
    }

    private fun checkPredicateAxiomExist(proof: Proof): Boolean {
        val impl = proof.expr
        if (impl is Impl) {
            if (impl.right is Exist) {
                val variable = (impl.right as Exist).left
                return checkSubstitution((impl.right as Exist).right, impl.left, variable)
            }
        }
        return false
    }

    private fun checkSubstitution(
        a: Expression,
        b: Expression,
        variable: Variable,
        replace: Expression? = null
    ): Boolean {
        val arr = ArrayList<Pair<Expression, Expression>>()
        a.diff(b, arr)
        if (arr.isEmpty()) return true
        val set1 = HashSet<Expression>()
        val set2 = HashSet<Expression>()
        arr.forEach {
            set1.add(it.first)
            set2.add(it.second)
        }
        val replaceExpr = arr[0].second
        if (set1.size == 1 && set1.contains(variable) && set2.size == 1) {
            val freeVars = HashSet<Variable>()
            replaceExpr.freeVars(HashSet(), freeVars)
            if (!a.replace(variable, HashSet(),freeVars)) return false
            if (replace != null) {
                if (set2.contains(replace)) {
                    return true
                }
                return false
            } else {
                return true
            }
        }
        return false
    }

    private fun check9thArithmeticAxiom(proof: Proof): Boolean {
        val impl = proof.expr
        if (impl is Impl) {
            val conj = impl.left
            if (conj is And) {
                val all = conj.right
                if (all is All) {
                    val variable = all.left
                    val impl2 = all.right
                    if (impl2 is Impl) {
                        if (impl2.left != impl.right) return false
                        if (!checkSubstitution(impl.right, conj.left, variable, Zero())) return false
                        if (!checkSubstitution(impl.right, impl2.right, variable, Apostrophe(variable))) return false
                        return true
                    }
                }

            }
        }
        return false
    }
}
