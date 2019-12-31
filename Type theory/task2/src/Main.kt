import logic.BetaReductor
import java.io.*
import java.util.*
import java.io.BufferedOutputStream
import java.lang.StringBuilder


object Main {


    //private val inp = BufferedReader(FileReader("tests/test.txt"))
    val inp = BufferedReader(InputStreamReader(System.`in`))
    val out = BufferedOutputStream(System.out)

    private fun readLine() = inp.readLine()
    //

    @JvmStatic
    fun main(args: Array<String>) {
        val nums = readLine().split(" ")
        val m = nums[0].toInt()
        val k = nums[1].toInt()
        val input = StringBuilder()
        var line = readLine()
        while (line != null && line != "ex") {
            input.append(line + "\n")
            line = readLine()
        }
        val res:List<String>
        try {
            res = BetaReductor().process(input.toString(), m, k)
        }
        catch (e:Exception){
            println("errrrorrrr")
            out.close()
            return
        }
        for ((ind, s) in res.withIndex()) {
            if (ind == res.size - 1) {
                out.write(s.toByteArray())
            } else {
                out.write("$s\n".toByteArray())
            }
        }
        out.close()
    }
}