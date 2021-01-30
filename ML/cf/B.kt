import java.io.*
import java.util.*
import kotlin.collections.ArrayList


object B {
    val fileName = ""
    //================================================================================

    class Info(
        var tp: Int = 0,
        var fp: Int = 0,
        var fn: Int = 0,
        var tn: Int = 0,
        var cnt: Int = 0,
        var prec: Double = 0.0,
        var recall: Double = 0.0,
        var fSc: Double = 0.0

    )

    fun run() {
        val k = IO.nextInt()
        val infos = Array(k) { Info() }
        var all = 0
        for (i in 0 until k) {
            for (j in 0 until k) {
                val cur = IO.nextInt()
                all += cur
                infos[i].cnt += cur
                if (i == j) {
                    infos[i].tp = cur
                } else {
                    infos[i].fp += cur
                    infos[j].fn += cur
                }
            }
        }
        infos.forEach {
            it.tn = all - it.fp - it.fn - it.tp
            it.recall = if (it.tp + it.fn != 0) it.tp.toDouble() / (it.tp + it.fn) else 0.0
            it.prec = if (it.tp + it.fp != 0) it.tp.toDouble() / (it.tp + it.fp) else 0.0
            it.fSc = if (it.recall + it.prec != 0.0) 2 * it.recall * it.prec / (it.recall + it.prec) else 0.0
        }
        val macroF = infos.map { it.fSc * it.cnt }.sum() / all

        val microPrec = infos.map { it.prec * it.cnt }.sum() / all
        val microRecall = infos.map { it.recall * it.cnt }.sum() / all
        val microF =
            if (microPrec + microRecall != 0.0) 2 * microPrec * microRecall / (microPrec + microRecall) else 0.0
        IO.println(microF)
        IO.print(macroF)

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