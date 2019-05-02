package processor

import AtoNotNotA
import NotNot10Axiom
import exceptions.ParserException
import expression.Expression
import expression.Impl
import expression.Not
import getAsExpressionList
import glivenkoAxiom
import parsers.Parser
import statement.*
import java.io.BufferedOutputStream

class GlivenkoProcessor {
    private val standardAxiomList = ArrayList<Axiom>()
    private val intAxiomList = ArrayList<Axiom>()
    private val hypothesisSet = HashSet<Hypothesis>()
    private val hypothesisList = ArrayList<Hypothesis>()
    private val proofList: ArrayList<Proof>
    private  var proofStrings: List<String>
     var whatToProve: Statement
        private set

    val notNotAProof = getAsExpressionList(AtoNotNotA)
    val notNot10AxProof = getAsExpressionList(NotNot10Axiom)
    val glivenkoAxProof = getAsExpressionList(glivenkoAxiom)

    val indexToProofsBlock = HashMap<Int, ProofsBlock>()


    @Throws(ParserException::class)
    constructor(
        standardAxioms: List<String>,
        intAxioms: List<String>,
        hypothesises: List<String>,
        proofLines: ArrayList<String>,
        statement: String
    ) {
        whatToProve = TrueStatement(Not(Not(Parser.parse(statement))), -1)
        for ((ind, ax) in standardAxioms.withIndex()) {
            standardAxiomList.add(Axiom(ax, ind))
        }
        for ((ind, ax) in intAxioms.withIndex()) {
            intAxiomList.add(Axiom(ax, ind))
        }
        for ((ind, hyp) in hypothesises.withIndex()) {
            val hypothesis = Hypothesis(hyp, ind)
            hypothesisList.add(hypothesis)
            hypothesisSet.add(hypothesis)
        }
        proofList = ArrayList(ValidationProcessor(standardAxiomList, hypothesises, proofLines, statement, false)
            .let {
                it.validate()
                return@let it.getNewProofList()
            }
        )
        proofStrings = proofLines
    }

    fun process() {
        for (i in 0 until proofList.size){
            generate(i)
        }
    }

    fun generate(index: Int) {
        val curProof = proofList[index]
        val curProofsBlock: ProofsBlock
        val letterToProof = HashMap<String, Expression>().apply {
            put("A", curProof.expr)
        }
        if (curProof.proofBy == ProofBy.AXIOM) {
            curProofsBlock = if (curProof.proofByIndex.first == 10-1) {
                val A = curProof.expr as Impl
                letterToProof["A"] = A.right
                ProofsBlock(notNot10AxProof, index, letterToProof)
            } else {
                ProofsBlock(notNotAProof, index, letterToProof)
            }
        } else if (curProof.proofBy == ProofBy.HYPOTHESIS) {
            curProofsBlock = ProofsBlock(notNotAProof, index, letterToProof)
        } else {
            val iIndex = curProof.proofByIndex.first
            val iTojIndex = curProof.proofByIndex.second
            letterToProof["A"] = proofList[iIndex].expr
            val B = proofList[iTojIndex].expr as Impl
            letterToProof["B"] =B.right
            curProofsBlock = ProofsBlock(glivenkoAxProof, index, letterToProof)
        }
        indexToProofsBlock[index] = curProofsBlock
    }

    fun print(out: BufferedOutputStream) {
        val statement = hypothesisList.joinToString(separator = ", ") { it.expr.toStr() } +
                (if (hypothesisList.isEmpty()) "" else " ") + "|- " + whatToProve.expr.toStr()
        out.write((statement).toByteArray())
        out.write("\n".toByteArray())
        for (i in 0 until proofList.size) {
            out.write(indexToProofsBlock[i].toString().toByteArray())
            out.write("\n".toByteArray())
        }
    }
}
