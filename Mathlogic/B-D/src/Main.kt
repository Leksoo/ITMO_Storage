import logicModel.StandardLogicAxioms
import processor.FullnessProcessor
import statement.Axiom
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader

object Main {

    //    var timeIn: Long = 0
//    var timeEndRead: Long = 0
//    var timeEndLoadData: Long = 0
//    var timeEndProcess: Long = 0
//    var timeEndWrite: Long = 0
    //
    //private val inp = BufferedReader(FileReader("test.txt"))
    private val inp = BufferedReader(InputStreamReader(System.`in`))
    private val out = BufferedOutputStream(System.out)


    @JvmStatic
    fun main(args: Array<String>) {
        //timeIn = System.currentTimeMillis()

        //  timeEndRead = System.currentTimeMillis()
        val axiomList = ArrayList<Axiom>()
        for ((ind, ax) in StandardLogicAxioms.axiomList.withIndex()) {
            axiomList.add(Axiom(ax, ind))
        }
        val processor = FullnessProcessor(
            axiomList, inp.readLine()
        )
        processor.generate(out)

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


}