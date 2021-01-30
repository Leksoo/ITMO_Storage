import java.io.*
import java.util.*
import kotlin.math.*

object E {
    val fileName = ""
    //================================================================================

    fun calcF(kernelDist: IntArray, param: DoubleArray, target: IntArray, b: Double): Double {
        var res = 0.0
        for (i in 0 until N) {
            res += param[i] * kernelDist[i].toDouble() * target[i].toDouble()
        }
        return res + b
    }


    var N = 0
    val tau = 1e-6
    val eps = 1e-6
    fun run() {
        N = IO.nextInt()
        val kernel = Array(N) { IntArray(N) }
        val target = IntArray(N)
        for (i in 0 until N) {
            for (j in 0 until N) {
                kernel[i][j] = IO.nextInt()
            }
            target[i] = IO.nextInt()
        }
        val C = IO.nextInt().toDouble()
        val lambda = DoubleArray(N)
        var b = 0.0
        var js = (0 until N).toList()
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < 1200) {
//        for (a in 0..1500) {
            js = js.shuffled()
            for (i in 0 until N) {
                // calc i-th object error
                val Ri = calcF(kernel[i], lambda, target, b) - target[i]
                if ((target[i] * Ri < -tau && lambda[i] < C) || (target[i] * Ri > tau && lambda[i] > 0)) {
                    // pick pair
                    val j = js[i]
                    if (i == j) continue
                    // calc j-th object error
                    val Rj = calcF(kernel[j], lambda, target, b) - target[j]
                    // calc L, H
                    var L: Double
                    var H: Double
                    if (target[i] == target[j]) {
                        val g = lambda[i] + lambda[j]
                        if (g > C) {
                            L = g - C.toDouble()
                            H = C.toDouble()
                        } else {
                            L = 0.0
                            H = g
                        }
                    } else {
                        val g = lambda[i] - lambda[j]
                        if (g > 0.0) {
                            L = 0.0
                            H = C.toDouble() - g
                        } else {
                            L = -g
                            H = C.toDouble()
                        }
                    }
                    if (L == H) {
                        continue
                    }
                    val nu: Double = 2.0 * kernel[i][j] - kernel[i][i].toDouble() - kernel[j][j].toDouble()
                    // calc new lambda[j]
                    var lambdaJ2: Double
                    if (nu < -eps) {
                        val lambdaJ1 = lambda[j] - target[j] * (Ri - Rj) / nu
                        lambdaJ2 = if (lambdaJ1 >= H) {
                            H
                        } else if (lambdaJ1 > L && lambdaJ1 < H) {
                            lambdaJ1
                        } else {
                            L
                        }
                    } else {
                        continue
//                        val c1 = nu / 2.0
//                        val c2 = target[j] * (Ri - Rj) - nu * lambda[j]
//                        val L1 = c1 * L * L + c2 * L
//                        val H1 = c1 * H * H + c2 * H
//                        lambdaJ2 = if (L1 > H1 + eps) {
//                            L
//                        } else if (H1 - eps <= L1 && L1 <= H1 + eps) {
//                            lambda[j]
//                        } else {
//                            H
//                        }
                    }
                    if (abs(lambdaJ2 - lambda[j]) < eps) {
                        continue
                    }
                    // calc new lambda[i]
                    val s = target[i] * target[j].toDouble()
                    val lambdaI1 = lambda[i] - s * (lambdaJ2 - lambda[j])
                    var lambdaI2: Double
//                    if (lambdaI1 < 0.0) {
//                        lambdaJ2 = s * lambdaI1
//                        lambdaI2 = 0.0
//                    } else if (lambdaI1 > C) {
//                        lambdaJ2 = s * (lambdaI1 - C)
//                        lambdaI2 = C
//                    } else {
                    lambdaI2 = lambdaI1
//                    }
                    // calc new w0 = b
                    val b1 = b - Ri - target[i] * (lambdaI2 - lambda[i]) * kernel[i][i] -
                            target[j] * (lambdaJ2 - lambda[j]) * kernel[i][j]
                    val b2 = b - Rj - target[i] * (lambdaI2 - lambda[i]) * kernel[i][j] -
                            target[j] * (lambdaJ2 - lambda[j]) * kernel[j][j]
                    val b3 = (b1 + b2) / 2.0
                    b = if (lambda[i] > 0.0 && lambda[i] < C) {
                        b1
                    } else if (lambda[j] > 0.0 && lambda[j] < C) {
                        b2
                    } else {
                        b3
                    }

                    lambda[i] = lambdaI2
                    lambda[j] = lambdaJ2

                }
            }
        }

        lambda.forEach {
            if (abs(it) < 0.00000001) {
                IO.println(0.0)
            } else {
                IO.println(it)
            }
        }
        if (abs(b) < 0.00000001) {
            IO.println(0.0)
        } else {
            IO.println(b)
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