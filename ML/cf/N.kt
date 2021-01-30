import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

object N {
    val fileName = ""

    //================================================================================

    fun run() {
        val K = IO.nextInt()
        val N = IO.nextInt()
        val classObjs = MutableList(K) { ArrayList<Int>() }
        val objs = ArrayList<Int>()
        repeat(N) {
            val x = IO.nextInt()
            objs.add(x)
            classObjs[IO.nextInt() - 1].add(x)
        }
        classObjs.forEach {
            it.sort()
        }
        objs.sort()
        var innerClass = 0L
        var crossClass = 0L
        for (k in 0 until K) {
            for (i in classObjs[k].indices) {
                innerClass += 2L * (2 * i + 1 - classObjs[k].size).toLong() * classObjs[k][i].toLong()
            }
        }
        crossClass -= innerClass
        for (i in objs.indices) {
            crossClass += 2L * (2 * i + 1 - objs.size).toLong() * objs[i].toLong()
        }
        IO.println(innerClass)
        IO.println(crossClass)

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