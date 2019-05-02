package diskrLab10


import java.io.*
import java.util.*


object D {
    const val fileName = ""

    //================================================================================
    var pos = 0
    val n = 7

    fun countSeq(previousRes: LongArray): LongArray {
        val curResult = LongArray(n)
        curResult[0] = 1
        for (w in 1 until n) {
            for (i in 1..w) {
                curResult[w] += previousRes[i] * curResult[w - i]
            }
        }
        return curResult
    }

    fun countMSet(previousRes: LongArray): LongArray {
        val curResult = LongArray(n)
        val m = Array(n) { LongArray(n) }
        for (i in 0 until n) {
            m[0][i] = 1
        }
        curResult[0] = 1
        for (w in 1 until n) {
            for (k in 1 until n) {
                for (i in 0..(w / k)) {
                    val up = previousRes[k] + i - 1
                    val down = i
                    var comb = 1L
                    for (c in (up - down + 1)..up) {
                        comb *= c
                    }
                    for (c in 1..down) {
                        comb /= c
                    }

                    m[w][k] += m[w - i * k][k - 1] * comb
                }
            }
            curResult[w] = m[w][w]
        }
        return curResult
    }

    fun countPair(previousRes1: LongArray, previousRes2: LongArray): LongArray {
        val curResult = LongArray(n)
        for (w in 0 until n) {
            for (i in 0..w) {
                curResult[w] += previousRes1[i] * previousRes2[w - i]
            }
        }
        return curResult
    }

    fun process(): LongArray {
        when (str[pos]) {
            'L' -> {
                pos++
                val res = countSeq(process())
                pos++
                return res

            }
            'S' -> {
                pos++
                val res = countMSet(process())
                pos++
                return res

            }
            'P' -> {
                pos++
                val res1 = process()
                pos++
                val res2 = process()
                pos++
                return countPair(res1, res2)
            }
            'B' -> {
                pos++
                return LongArray(n).apply { this[1] = 1 }
            }
            '(' -> {
                pos++
                return process()
            }
        }
        return LongArray(n)
    }

    var str: String = ""
    fun run() {
        str = IO.next()
        process().forEach { IO.print("$it ") }
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