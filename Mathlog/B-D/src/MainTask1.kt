import exceptions.ValidationProofException
import logicModel.StandardLogicAxioms
import processor.INCORRECT_PROOF_MESSAGE
import processor.ValidationProcessor
import statement.Axiom
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader


object MainTask1 {

    //    var timeIn: Long = 0
//    var timeEndRead: Long = 0
//    var timeEndLoadData: Long = 0
//    var timeEndProcess: Long = 0
//    var timeEndWrite: Long = 0
    //
    //private val inp = BufferedReader(FileReader("test.txt"))
    private val inp = BufferedReader(InputStreamReader(System.`in`))
    private val out = BufferedOutputStream(System.out)

    //

    @JvmStatic
    fun main(args: Array<String>) {
        //timeIn = System.currentTimeMillis()
        try {
            val splittedStatement =
                getHypothesisesAndTarget(inp.readLine() ?: throw ValidationProofException(INCORRECT_PROOF_MESSAGE))
            val proofLines = getProofLines(inp)
            //  timeEndRead = System.currentTimeMillis()
            val axiomList = ArrayList<Axiom>()
            for ((ind, ax) in StandardLogicAxioms.axiomList.withIndex()) {
                axiomList.add(Axiom(ax, ind))
            }
            val validationProcessor = ValidationProcessor(
                axiomList,
                splittedStatement.first, proofLines, splittedStatement.second, true
            )
            //timeEndLoadData = System.currentTimeMillis()
            validationProcessor.validate()
            //timeEndProcess = System.currentTimeMillis()
            validationProcessor.printProofsWithAnnotations(out)
            //out.write(validationProcessor.getNewProofListInListSyntax().toByteArray())
            //timeEndWrite = System.currentTimeMillis()
        } catch (e: ValidationProofException) {
            //timeEndProcess = System.currentTimeMillis()
            println(e.message)
            //timeEndWrite = System.currentTimeMillis()
        }
//        println("in : $timeIn")
//        println("endRead : ${timeEndRead - timeIn}")
//        println("endLoad : ${timeEndLoadData - timeEndRead}")
//        println("endProcess : ${timeEndProcess- timeEndLoadData}")
//        println("endWrite : ${timeEndWrite- timeEndProcess}")
        out.close()

    }


}