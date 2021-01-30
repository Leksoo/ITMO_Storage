//import java.io.*
//import java.util.*
//import kotlin.collections.ArrayList
//import kotlin.math.*
//
//
//object C {
//    val fileName = ""
//    //================================================================================
//
//    class Obj(
//        val attrs: ArrayList<Int>,
//        val target: Int
//    ) {
//        var distance = 0.0
//    }
//
//    enum class Distance(val itemName: String, val calc: (a: List<Int>, b: List<Int>) -> Double) {
//        MANHATTAN("manhattan", { a, b -> a.zip(b).map { abs(it.first - it.second) }.sum().toDouble() }),
//        EUCLIDEAN(
//            "euclidean",
//            { a, b ->
//                a.zip(b).map { (it.first - it.second) * (it.first - it.second) }.sum().toDouble().let { sqrt(it) }
//            }
//        ),
//        CHEBYSHEV("chebyshev", { a, b -> a.zip(b).map { abs(it.first - it.second) }.max()!!.toDouble() })
//    }
//
//    enum class Kernel(val itemName: String, val calc: (u: Double) -> Double) {
//        UNIFORM("uniform", { u -> if (abs(u) < 1.0) 1.0 / 2 else 0.0 }),
//        TRIANGULAR("triangular", { u -> if (abs(u) < 1.0) 1.0 - abs(u) else 0.0 }),
//        EPANECHNIKOV("epanechnikov", { u -> if (abs(u) < 1.0) 3.0 / 4 * (1 - abs(u * u)) else 0.0 }),
//        QUARTIC("quartic", { u -> if (abs(u) < 1.0) 15.0 / 16 * (1 - u * u) * (1 - u * u) else 0.0 }),
//        TRIWEIGHT("triweight", { u -> if (abs(u) < 1.0) 35.0 / 32 * (1 - u * u).pow(3) else 0.0 }),
//        TRICUBE("tricube", { u -> if (abs(u) < 1.0) 70.0 / 81 * (1 - abs(u).pow(3)).pow(3) else 0.0 }),
//        GAUSSIAN("gaussian", { u -> 1.0 / sqrt(2.0 * PI) * E.pow(-1.0 / 2 * u * u) }),
//        COSINE("cosine", { u -> if (abs(u) < 1.0) PI / 4.0 * cos(PI / 2 * u) else 0.0 }),
//        LOGISTIC("logistic", { u -> 1.0 / (E.pow(u) + 2 + E.pow(-u)) }),
//        SIGMOID("sigmoid", { u -> 2.0 / PI / (E.pow(u) + E.pow(-u)) })
//    }
//
//    enum class Window(val itemName: String) {
//        FIXED("fixed"),
//        VARIABLE("variable")
//    }
//
//    fun run() {
//        val N = IO.nextInt()
//        val M = IO.nextInt()
//        val objs = ArrayList<Obj>()
//        for (i in 0 until N) {
//            val attrs = ArrayList<Int>()
//            for (j in 0 until M) {
//                attrs.add(IO.nextInt())
//            }
//            objs.add(Obj(attrs, IO.nextInt()))
//        }
//        val q = ArrayList<Int>()
//        for (j in 0 until M) {
//            q.add(IO.nextInt())
//        }
//        val distance = IO.next().let { name ->
//            Distance.values().find { it.itemName == name }!!
//        }
//        val kernel = IO.next().let { name ->
//            Kernel.values().find { it.itemName == name }!!
//        }
//        val window = IO.next().let { name ->
//            Window.values().find { it.itemName == name }!!
//        }
//        val K = IO.nextInt()
//        objs.forEach {
//            it.distance = distance.calc.invoke(it.attrs, q)
//        }
//        objs.sortBy { it.distance }
//        var windowDivider = 0.0
//        if (window == Window.VARIABLE) {
//            windowDivider = objs[K].distance
//            if (windowDivider == 0.0) {
//                val res = objs.filter { it.distance == 0.0 }.map { it.target }.average()
//                IO.print(res)
//                return
//            }
//        } else if (window == Window.FIXED) {
//            windowDivider = K.toDouble()
//            if (windowDivider == 0.0) {
//                var res = objs.filter { it.distance == 0.0 }.map { it.target }.average()
//                if (res.isNaN()) {
//                    res = objs.map { it.target }.average()
//                }
//                IO.print(res)
//                return
//            }
//        }
//        val kernelRes = objs.map { kernel.calc.invoke(it.distance / windowDivider) }
//        if (kernelRes.sum() == 0.0) {
//            val res = objs.map { it.target }.average()
//            IO.print(res)
//            return
//        }
//        val res = objs.zip(kernelRes).map { it.first.target * it.second }.sum() / kernelRes.sum()
//        IO.print(res)
//    }
////==================================================================================
//
//    @JvmStatic
//    fun main(args: Array<String>) {
//        this.run()
//        IO.writer.flush()
//        IO.reader.close()
//
//    }
//
//    object IO {
//        val reader: BufferedReader
//        val writer: PrintWriter
//        private var tokenizer: StringTokenizer? = null
//
//        init {
//            if (fileName.isEmpty()) {
//                reader = BufferedReader(InputStreamReader(System.`in`))
//                writer = PrintWriter(System.out)
//            } else {
//                reader = BufferedReader(FileReader("$fileName.in"))
//                writer = PrintWriter(FileWriter("$fileName.out"))
//            }
//        }
//
//        fun next(): String {
//            while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
//                val line = reader.readLine() ?: throw NullPointerException()
//                if (line == "") {
//                    return ""
//                }
//                tokenizer = StringTokenizer(line)
//            }
//            return tokenizer!!.nextToken()
//        }
//
//        fun nextLine(): String {
//            return reader.readLine()
//        }
//
//        fun nextInt(): Int {
//            return next().toInt()
//        }
//
//        fun nextDouble(): Double {
//            return next().toDouble()
//        }
//
//        fun nextLong(): Long {
//            return next().toLong()
//        }
//
//        fun <T> print(obj: T) {
//            writer.print(obj)
//        }
//
//        fun <T> println(obj: T) {
//            writer.println(obj)
//        }
//    }
//}