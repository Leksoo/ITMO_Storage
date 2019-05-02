package diskrLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max


object A {
    const val fileName = ""

    //================================================================================
    val MOD: Long = 998244353
    var n = 0
    var m = 0
    var maxN = 1001
    lateinit var p: LongArray
    lateinit var q: LongArray
    fun run() {
        n = IO.nextInt() + 1
        m = IO.nextInt() + 1
        p = LongArray(maxN)
        q = LongArray(maxN)
        for (i in 0 until n) {
            p[i] = IO.nextLong()
        }
        for (i in 0 until m) {
            q[i] = IO.nextLong()
        }
        sum()
        multiply()
        div()
    }

    fun sum() {
        val sum = LongArray(maxN)
        for (i in 0 until maxN) {
            sum[i] = (p[i] + q[i]) % MOD
            sum[i] = sum[i] % MOD
        }
        var deg = 0
        for (i in maxN - 1 downTo 0) {
            if (sum[i] != 0L) {
                deg = i
                break
            }
        }
        IO.println(deg)
        for (i in 0..deg) {
            IO.print("${sum[i]} ")
        }
        IO.println("")
    }

    fun multiply() {
        val mult = LongArray(maxN * 3)
        for (i in 0 until n) {
            for (j in 0 until m) {
                mult[i + j] += (p[i] * q[j]) % MOD
                mult[i + j] = mult[i + j] % MOD
            }
        }
        var deg = 0
        for (i in maxN * 3 - 1 downTo 0) {
            if (mult[i] != 0L) {
                deg = i
                break
            }
        }
        IO.println(deg)
        for (i in 0..deg) {
            IO.print("${mult[i]} ")
        }
        IO.println("")
    }

    //c(n)=a(n)-sum(a(i-k)*b(k))
    fun div() {
        val div = LongArray(1000)
        for (i in 0 until 1000) {
            div[i] = p[i]
            for (k in 0 until i) {
                div[i] -= (div[k] * q[i - k])
                div[i] = div[i] % MOD
                while (div[i] < 0L) {
                    div[i] += MOD
                }
            }
        }

        for (i in 0 until 1000) {
            IO.print("${div[i]} ")
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
    }
}