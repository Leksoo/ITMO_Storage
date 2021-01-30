import javafx.scene.text.FontWeight
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.random.Random

object H2 {
    val fileName = ""

    //================================================================================


    var M = 0
    fun run() {
        M = IO.nextInt()
        val fValues = ArrayList<Int>()
        for (i in 0 until 2.0.pow(M).toInt()) {
            fValues.add(IO.nextInt())
        }
        if (fValues.sum() == 0) {
            IO.println(1)
            IO.println(1)
            repeat(M) {
                IO.print("-1.0 ")
            }
            IO.println("-1.0")
            return
        }
        IO.println(2)
        IO.println("${2.0.pow(M).toInt()} 1")
        for (i in 0 until 2.0.pow(M).toInt()) {
            var x = i.toString(2)
            while (x.length != M) {
                x = "0$x"
            }
            val values = x.reversed().map { it.toInt() - '0'.toInt()}
            IO.print(values.map { if (it == 0) -1.0 else 1.0 }.joinToString(" "))
            IO.println(" ${-values.sum().toDouble() + 0.5}")
        }
        IO.print(fValues.map { if (it == 0) 0.0 else 1.0 }.joinToString(" "))
        IO.println(" -0.5")
    }
    //==================================================================================

    @JvmStatic
    fun main(args: Array<String>) {
        this.run()
        IO.writer.flush()
        IO.reader.close()

    }

    object IO {
        val reader: BufferedReader
        val writer: PrintWriter
        private var tokenizer: StringTokenizer? = null

        init {
            if (fileName.isEmpty()) {
                reader = BufferedReader(InputStreamReader(System.`in`))
                writer = PrintWriter(System.out)
            } else {
                reader = BufferedReader(FileReader("$fileName.in"))
                writer = PrintWriter(FileWriter("$fileName.out"))
            }
        }

        fun next(): String {
            while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
                val line = reader.readLine() ?: throw NullPointerException()
                if (line == "") {
                    return ""
                }
                tokenizer = StringTokenizer(line)
            }
            return tokenizer!!.nextToken()
        }

        fun nextLine(): String {
            return reader.readLine()
        }

        fun nextInt(): Int {
            return next().toInt()
        }

        fun nextDouble(): Double {
            return next().toDouble()
        }

        fun nextLong(): Long {
            return next().toLong()
        }

        fun <T> print(obj: T) {
            writer.print(obj)
        }

        fun <T> println(obj: T) {
            writer.println(obj)
        }
    }
}