package diskrLab10

import java.io.*
import java.util.*


object F {
    const val fileName = ""

    //================================================================================
    val MOD: Long = 104857601
    var k = 0
    var n: Long = 0
    lateinit var start: LongArray
    lateinit var coeff: LongArray
    fun run() {
        k = IO.nextInt()
        n = IO.nextLong() - 1
        start = LongArray(2 * k)
        coeff = LongArray(k)
        var q = LongArray(k + 1)
        for (i in 0 until k) {
            start[i] = IO.nextLong()
        }
        q[0] = 1
        for (i in 0 until k) {
            val c = IO.nextLong()
            coeff[i] = c
            q[i + 1] = (-c % MOD + MOD) % MOD
        }
        while (n >= k) {
            for (i in k until 2 * k) {
                for (j in 1..k) {
                    start[i] += ((-q[j] % MOD + MOD) % MOD * start[i - j]) % MOD
                    start[i] %= MOD
                }
            }
            // q2 = q(-t)
            val q2 = q.copyOf()
            for (i in 1..k step 2) {
                q2[i] = (-q2[i] % MOD + MOD) % MOD
            }
            val mult = LongArray(2 * (k + 1))
            for (i in 0..k) {
                for (j in 0..k) {
                    mult[i + j] += (q[i] * q2[j]) % MOD
                    mult[i + j] = (mult[i + j] % MOD + MOD) % MOD
                }
            }
            start = start.filterIndexed { ind, _ -> ind.toLong() % 2 == n % 2 }.toLongArray().copyOf(2 * k)
            q = mult.filter { it != 0L }.toLongArray()
            n /= 2
        }
        IO.println(start[n.toInt()])

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