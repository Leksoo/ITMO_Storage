package diskrLab10


import java.io.*
import java.util.*
import kotlin.collections.HashSet


object C {
    const val fileName = ""

    //================================================================================
    val MOD = 1000000007L
    var k: Int = 0
    var m: Int = 0
    val w = HashSet<Int>()
    lateinit var count: LongArray
    fun run() {
        k = IO.nextInt()
        m = IO.nextInt()
        count = LongArray(m + 1)
        count[0] = 1
        for (i in 0 until k) {
            w.add(IO.nextInt())
        }
        val subTrees = LongArray(m + 1)
        subTrees[0] = 1
        for (curW in 1..m) {
            if (curW > 1) {
                // subtrees[w] = leftTrees[i] * rightTrees[w-i]
                for (tr in 0 until curW) {
                    subTrees[curW - 1] += (count[tr] * count[curW - 1 - tr]) % MOD
                    subTrees[curW - 1] = subTrees[curW - 1] % MOD
                }
            }
            for (rootW in w) {
                if (rootW <= curW) {
                    count[curW] += subTrees[curW - rootW] % MOD
                    count[curW] %= MOD
                }
            }
            IO.print("${count[curW]} ")
        }
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

        fun println() {
            writer.println()
        }
    }
}