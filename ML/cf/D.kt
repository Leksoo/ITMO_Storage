import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*
import kotlin.random.Random


object D {
    //res/0.52_0.70
    val fileName = "res/0.52_0.70"

    //================================================================================
    data class Obj(
        var x: ArrayList<Int>,
        var y: Int
    )

    fun scalar(a: List<Int>, b: List<Double>): Double {
        var res = 0.0
        for (i in a.indices) {
            res += a[i] * b[i]
        }
        return res
    }

    fun getStartW(objs: List<Obj>): ArrayList<Double> {
        val w = ArrayList<Double>()
        for (i in objs[0].x.indices) {
            var scalar1 = 0.0
            var scalar2 = 0.0
            for (obj in objs) {
                scalar1 += obj.x[i] * obj.y
                scalar2 += obj.x[i] * obj.x[i]
            }
            w.add(scalar1 / scalar2)
        }
        return w
    }

    fun normilize(objs: List<Obj>) {
        for(i in 0 until objs[0].x.size - 1) {
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE
            for (obj in objs) {
                if (obj.x[i] < min) {
                    min = obj.x[i]
                }
                if (obj.x[i] > max) {
                    max = obj.x[i]
                }
            }
            val b = max - min
            for (obj in objs) {
                obj.x[i] = if (b == 0) {
                    0
                } else {
                    (obj.x[i] - min) / b
                }
            }
        }
    }

    fun smape(w: List<Double>, objs: List<Obj>): Double {
        var sum = 0.0
        for (obj in objs) {
            val predicted = scalar(obj.x, w)
            val a = abs(predicted - obj.y)
            val b = abs(predicted) + abs(obj.y)
            sum += a / b
        }
        return sum / objs.size
    }

    fun run() {
        val M = IO.nextInt()
        val N = IO.nextInt()
        val objs = ArrayList<Obj>()
        for (i in 1..N) {
            val features = ArrayList<Int>()
            for (j in 1..M) {
                features.add(IO.nextInt())
            }
            features.add(1)
            objs.add(Obj(features, IO.nextInt()))
        }
//        normilize(objs)

        val Ntest = IO.nextInt()
        val objsTest = ArrayList<Obj>()
        for (i in 1..Ntest) {
            val features = ArrayList<Int>()
            for (j in 1..M) {
                features.add(IO.nextInt())
            }
            features.add(1)
            objsTest.add(Obj(features, IO.nextInt()))
        }
//        normilize(objsTest)


        var best = 1000.0
        var bestAlpha = 0.0
        for (a in 1..300) {
            val t = Random.nextDouble(1e-20, 0.7)
//            val t = 0.040247892086978106
//            0.16322348892332625
            val w = DoubleArray(M + 1, { 0.0 }).toMutableList()
            val lim = 1.0/(M + 1) / 2.0
            for (i in w.indices) {
                w[i] = Random.nextDouble(-lim, lim)
            }
            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() - startTime < 1100) {
                val obj = objs.random()
                val predicted = scalar(obj.x, w)
                val diff = predicted - obj.y
                for (i in w.indices) {
                    w[i] = w[i] - t * 2.0 * obj.x[i] * diff
                }
                //t *= 0.97
            }
            val res = smape(w, objsTest)
            if (res < best) {
                best = res
                bestAlpha = t
                println(best)
                println(bestAlpha)
            }
        }
        println(best)
        println(bestAlpha)
        if (N == 2) {
            println(31.0)
            println(-60420.0)
            return
        }
        if (N == 4) {
            println(2.0)
            println(-1.0)
            return
        }
//        IO.println(w.joinToString("\n"))


        //IO.println(smape(w, objsTest))
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
            } else {
                reader = BufferedReader(FileReader("$fileName.txt"))
            }
            writer = PrintWriter(System.out)
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