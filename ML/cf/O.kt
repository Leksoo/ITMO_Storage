import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

object O {
    val fileName = ""

    //================================================================================

    fun run() {
        val K = IO.nextInt()
        val N = IO.nextInt()
        val classObjs = MutableList(K) { ArrayList<Int>() }
        repeat(N) {
            val x = IO.nextInt()
            classObjs[x - 1].add(IO.nextInt())
        }
        var MDyxTotal = 0.0
        for (cl in classObjs) {
            if (cl.isEmpty()) continue
            val Pyx = cl.size.toDouble() / N
            val Myx = cl.sum().toDouble() / cl.size
            val Dyx = cl.map { (it.toDouble() - Myx).pow(2) }.sum() / cl.size
            val MDyx = Pyx * Dyx
            MDyxTotal += MDyx
        }
        IO.println(MDyxTotal)

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