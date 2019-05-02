import exceptions.ValidationProofException
import logicModel.IntLogicAxioms
import logicModel.StandardLogicAxioms
import processor.GlivenkoProcessor
import processor.INCORRECT_PROOF_MESSAGE
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

object MainTask2 {

    //    var timeIn: Long = 0
//    var timeEndRead: Long = 0
//    var timeEndLoadData: Long = 0
//    var timeEndProcess: Long = 0
//    var timeEndWrite: Long = 0
    //
    //private val inp = BufferedReader(FileReader("test.txt"))
    private val inp = BufferedReader(InputStreamReader(System.`in`))
    private val out = BufferedOutputStream(System.out)

    private fun readLine() = inp.readLine()

    @JvmStatic
    fun main(args: Array<String>) {
        //timeIn = System.currentTimeMillis()
        val splittedStatement =
            parseStatement(readLine())
        val proofLines = ArrayList<String>()
        var line = readLine()
        while (line != null && line != "ex") {
            proofLines.add(line)
            line = readLine()
        }
        //  timeEndRead = System.currentTimeMillis()
        val processor = GlivenkoProcessor(
            StandardLogicAxioms.axiomList, IntLogicAxioms.axiomList,
            splittedStatement.first, proofLines, splittedStatement.second
        )
        processor.process()
        processor.print(out)
        //timeEndLoadData = System.currentTimeMillis()
        //timeEndProcess = System.currentTimeMillis()
        //timeEndWrite = System.currentTimeMillis()

//        println("in : $timeIn")
//        println("endRead : ${timeEndRead - timeIn}")
//        println("endLoad : ${timeEndLoadData - timeEndRead}")
//        println("endProcess : ${timeEndProcess- timeEndLoadData}")
//        println("endWrite : ${timeEndWrite- timeEndProcess}")
        out.close()

    }

    private fun parseStatement(statement: String): Pair<List<String>, String> {
        val splitByTurnstile = statement.split("|-")
        if (splitByTurnstile.size != 2) {
            throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
        }
        return Pair(if (splitByTurnstile[0] == "") emptyList() else splitByTurnstile[0].split(","), splitByTurnstile[1])
    }

}