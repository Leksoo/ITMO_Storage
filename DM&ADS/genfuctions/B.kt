package diskrLab10


import java.io.*
import java.util.*


object B {
    const val fileName = ""

    //================================================================================

    class Rat(
            var x: Long,
            var y: Long
    )

    val MOD: Long = 998244353
    var n = 0
    var m = 0
    lateinit var p: LongArray
    lateinit var sqrtCoefs: LongArray
    lateinit var expCoefs: LongArray
    lateinit var logCoefs: LongArray
    fun run() {
        n = IO.nextInt() + 1
        m = IO.nextInt()
        p = LongArray(101)
        sqrtCoefs = LongArray(m)
        expCoefs = LongArray(m)
        logCoefs = LongArray(m)
        for (i in 0 until n) {
            p[i] = IO.nextLong()
        }
        sqrtCoefs[0] = 1
        for (i in 1 until m) {
            val up = (3 - 2 * i) * sqrtCoefs[i - 1]
            val down = 2L * i
            val rat = Rat(0, 0)
            gcd(down, MOD, rat)
            sqrtCoefs[i] = (((up % MOD + MOD) % MOD) * ((rat.x % MOD + MOD) % MOD)) % MOD
        }
        expCoefs[0] = 1
        for (i in 1 until m) {
            val up = expCoefs[i - 1]
            val down = i.toLong()
            val rat = Rat(0, 0)
            gcd(down, MOD, rat)
            expCoefs[i] = (((up % MOD + MOD) % MOD) * ((rat.x % MOD + MOD) % MOD)) % MOD
        }
        logCoefs[0] = 0
        if (m > 1) {
            logCoefs[1] = 1L
        }
        for (i in 2 until m) {
            val up = (1L - i) * logCoefs[i - 1]
            val down = (i).toLong()
            val rat = Rat(0, 0)
            gcd(down, MOD, rat)
            logCoefs[i] = (((up % MOD + MOD) % MOD) * ((rat.x % MOD + MOD) % MOD)) % MOD
        }
        val sqrtRes = LongArray(m)
        sqrtRes[0] = sqrtCoefs[0]
        val expRes = LongArray(m)
        expRes[0] = expCoefs[0]
        val logRes = LongArray(m)
        logRes[0] = logCoefs[0]
        val pDeg = p.copyOf()
        for (i in 1 until m) {
            if (i > 1) {
                val tmpP = LongArray(101)
                for (ii in 0 until 101) {
                    for (jj in 0 until 101) {
                        if (ii + jj < 101) {
                            tmpP[ii + jj] += (p[ii] * pDeg[jj]) % MOD
                            tmpP[ii + jj] = tmpP[ii + jj] % MOD
                        }
                    }
                }
                 p = tmpP.copyOf()
            }
            for (j in 0 until 101) {
                if (j < m) {
                    sqrtRes[j] += (sqrtCoefs[i] * p[j]) % MOD
                    sqrtRes[j] = sqrtRes[j] % MOD
                    logRes[j] += (logCoefs[i] * p[j]) % MOD
                    logRes[j] = logRes[j] % MOD
                    expRes[j] += (expCoefs[i] * p[j]) % MOD
                    expRes[j] = expRes[j] % MOD
                }
            }
        }
        sqrtRes.forEach { IO.print("$it ") }
        IO.println()
        expRes.forEach { IO.print("$it ") }
        IO.println()
        logRes.forEach { IO.print("$it ") }


    }

    fun gcd(a: Long, b: Long, rat: Rat) {
        if (a == 0L) {
            rat.x = 0L
            rat.y = 1L
            return
        }
        val tmpRat = Rat(0, 0)
        val d = gcd(b % a, a, tmpRat)
        rat.x = tmpRat.y - (b / a) * tmpRat.x
        rat.y = tmpRat.x
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