import logic.Typer
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
        val input = StringBuilder()
        var line = readLine()
        while (line != null) {
            input.append(line + "\n")
            line = readLine()
        }
        out.write(Typer().process(input.toString()).toByteArray())
        out.close()
    }
}