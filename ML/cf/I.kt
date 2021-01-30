import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.tanh

object I {
    val fileName = ""

    //================================================================================

    sealed class Vertex(
        val id: Int
    ) {
        var forwardMatrix: Array<DoubleArray>? = null

        abstract fun calcForward()
        abstract fun calcBackward(dx: Array<DoubleArray>)

        fun getSize(): Pair<Int, Int> {
            val rows = forwardMatrix!!.size
            val cols = forwardMatrix!![0].size
            return Pair(rows, cols)
        }

        class InputV(
            id: Int,
            val rows: Int,
            val columns: Int
        ) : Vertex(id) {

            var dx = Array(rows) { DoubleArray(columns) { 0.0 } }

            companion object {
                fun read(id: Int): InputV {
                    return InputV(id, IO.nextInt(), IO.nextInt())
                }
            }

            override fun calcForward() {
            }

            override fun calcBackward(dx: Array<DoubleArray>) {
                for (i in 0 until rows) {
                    for (j in 0 until columns) {
                        this.dx[i][j] += dx[i][j]
                    }
                }
            }
        }

        class TnhV(
            id: Int,
            val arg: Int
        ) : Vertex(id) {
            companion object {
                fun read(id: Int): TnhV {
                    return TnhV(id, readV())
                }
            }

            override fun calcForward() {
                vertices[arg].calcForward()
                val (rows, cols) = vertices[arg].getSize()
                val matrix = Array(rows) { DoubleArray(cols) { 0.0 } }
                for (i in 0 until rows) {
                    for (j in 0 until cols) {
                        matrix[i][j] = vertices[arg].forwardMatrix!![i][j].let {
                            tanh(it)
                        }
                    }
                }
                forwardMatrix = matrix
            }

            override fun calcBackward(dx: Array<DoubleArray>) {
                val (rows, cols) = vertices[arg].getSize()
                val matrix = Array(rows) { DoubleArray(cols) { 0.0 } }
                for (i in 0 until rows) {
                    for (j in 0 until cols) {
                        matrix[i][j] = vertices[arg].forwardMatrix!![i][j].let {
                            1.0 - tanh(it).pow(2)
                        }
                        matrix[i][j] *= dx[i][j]
                    }
                }
                vertices[arg].calcBackward(matrix)
            }
        }

        class RluV(
            id: Int,
            val alpha: Int,
            val arg: Int
        ) : Vertex(id) {
            companion object {
                fun read(id: Int): RluV {
                    return RluV(id, IO.nextInt(), readV())
                }
            }

            override fun calcForward() {
                vertices[arg].calcForward()
                val (rows, cols) = vertices[arg].getSize()
                val matrix = Array(rows) { DoubleArray(cols) { 0.0 } }
                for (i in 0 until rows) {
                    for (j in 0 until cols) {
                        matrix[i][j] = vertices[arg].forwardMatrix!![i][j].let {
                            if (it >= 0) {
                                it
                            } else {
                                1.0 / alpha * it
                            }
                        }
                    }
                }
                forwardMatrix = matrix
            }

            override fun calcBackward(dx: Array<DoubleArray>) {
                val (rows, cols) = vertices[arg].getSize()
                val matrix = Array(rows) { DoubleArray(cols) { 0.0 } }
                for (i in 0 until rows) {
                    for (j in 0 until cols) {
                        matrix[i][j] = vertices[arg].forwardMatrix!![i][j].let {
                            if (it >= 0) {
                                1.0
                            } else {
                                1.0 / alpha
                            }
                        }
                        matrix[i][j] *= dx[i][j]
                    }
                }
                vertices[arg].calcBackward(matrix)
            }
        }

        class MulV(
            id: Int,
            val arg1: Int,
            val arg2: Int
        ) : Vertex(id) {
            companion object {
                fun read(id: Int): MulV {
                    return MulV(id, readV(), readV())
                }
            }

            override fun calcForward() {
                vertices[arg1].calcForward()
                vertices[arg2].calcForward()
                val (rows1, cols1) = vertices[arg1].getSize()
                val (rows2, cols2) = vertices[arg2].getSize()
                val matrix = Array(rows1) { DoubleArray(cols2) { 0.0 } }
                for (i in 0 until rows1) {
                    for (j in 0 until cols2) {
                        for (k in 0 until cols1) {
                            matrix[i][j] +=
                                vertices[arg1].forwardMatrix!![i][k] * vertices[arg2].forwardMatrix!![k][j]
                        }
                    }
                }
                forwardMatrix = matrix
            }

            override fun calcBackward(dx: Array<DoubleArray>) {
                // (A * B)/dA = DX * B^t ([dx.size x rows2])
                // (A * B)/dB = A^t * DX ([cols1 x dx[0].size)
                val (rows1, cols1) = vertices[arg1].getSize()
                val (rows2, cols2) = vertices[arg2].getSize()
                val m1 = Array(dx.size) { DoubleArray(rows2) { 0.0 } }
                val m2 = Array(cols1) { DoubleArray(dx[0].size) { 0.0 } }
                for (i in 0 until dx.size) {
                    for (j in 0 until rows2) {
                        for (k in 0 until cols2) {
                            m1[i][j] +=
                                dx[i][k] * vertices[arg2].forwardMatrix!![j][k]
                            m2[j][k] +=
                                vertices[arg1].forwardMatrix!![i][j] * dx[i][k]
                        }
                    }
                }
                vertices[arg1].calcBackward(m1)
                vertices[arg2].calcBackward(m2)
            }
        }

        class SumV(
            id: Int,
            val args: List<Int>
        ) : Vertex(id) {
            companion object {
                fun read(id: Int): SumV {
                    val count = IO.nextInt()
                    val args = ArrayList<Int>()
                    repeat(count) {
                        args.add(readV())
                    }
                    return SumV(id, args)
                }
            }

            override fun calcForward() {
                for (i in args) {
                    vertices[i].calcForward()
                }
                val (rows, cols) = vertices[args[0]].getSize()
                val matrix = Array(rows) { DoubleArray(cols) { 0.0 } }
                for (i in 0 until rows) {
                    for (j in 0 until cols) {
                        for (k in args) {
                            matrix[i][j] += vertices[k].forwardMatrix!![i][j]
                        }
                    }
                }
                forwardMatrix = matrix
            }

            override fun calcBackward(dx: Array<DoubleArray>) {
                for (i in args) {
                    vertices[i].calcBackward(dx)
                }
            }
        }

        class HadV(
            id: Int,
            val args: List<Int>
        ) : Vertex(id) {

            companion object {
                fun read(id: Int): HadV {
                    val count = IO.nextInt()
                    val args = ArrayList<Int>()
                    repeat(count) {
                        args.add(readV())
                    }
                    return HadV(id, args)
                }
            }

            override fun calcForward() {
                for (i in args) {
                    vertices[i].calcForward()
                }
                val (rows, cols) = vertices[args[0]].getSize()
                val matrix = Array(rows) { DoubleArray(cols) { 1.0 } }
                for (i in 0 until rows) {
                    for (j in 0 until cols) {
                        for (k in args) {
                            matrix[i][j] *= vertices[k].forwardMatrix!![i][j]
                        }
                    }
                }
                forwardMatrix = matrix
            }

            override fun calcBackward(dx: Array<DoubleArray>) {
                val (rows, cols) = vertices[args[0]].getSize()
                for (i in args.indices) {
                    val matrix = Array(rows) { DoubleArray(cols) { 1.0 } }
                    for (j in args.indices) {
                        if (i == j) continue
                        adamar(matrix, vertices[args[j]].forwardMatrix!!)
                    }
                    adamar(matrix, dx)
                    vertices[args[i]].calcBackward(matrix)
                }
            }

            private fun adamar(m1: Array<DoubleArray>, m2: Array<DoubleArray>) {
                for (i in m1.indices) {
                    for (j in m1[0].indices) {
                        m1[i][j] *= m2[i][j]
                    }
                }
            }
        }
    }

    fun readV(): Int {
        return IO.nextInt() - 1
    }

    fun readVertex(id: Int): Vertex? {
        when (IO.next()) {
            "had" -> return Vertex.HadV.read(id)
            "sum" -> return Vertex.SumV.read(id)
            "mul" -> return Vertex.MulV.read(id)
            "rlu" -> return Vertex.RluV.read(id)
            "tnh" -> return Vertex.TnhV.read(id)
            "var" -> return Vertex.InputV.read(id)
        }
        return null
    }

    var N = 0
    var M = 0
    var K = 0
    val vertices = ArrayList<Vertex>()
    val inputs = ArrayList<Vertex.InputV>()
    val outputs = ArrayList<Vertex>()
    val edgeList = ArrayList<ArrayList<Int>>()
    fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        K = IO.nextInt()
        repeat(N) {
            edgeList.add(ArrayList())
        }
        for (id in 0 until N) {
            val vertex = readVertex(id)!!
            if (id < M) {
                inputs.add(vertex as Vertex.InputV)
            }
            if (id >= N - K) {
                outputs.add(vertex)
            }
            vertices.add(vertex)
        }

        for (inputV in inputs) {
            val matrix = Array(inputV.rows) { DoubleArray(inputV.columns) }
            for (i in 0 until inputV.rows) {
                for (j in 0 until inputV.columns) {
                    matrix[i][j] = IO.nextInt().toDouble()
                }
            }
            inputV.forwardMatrix = matrix
        }

        for (v in outputs) {
            v.calcForward()
        }
        for (v in outputs) {
            printMatrix(v.forwardMatrix!!)
        }

        for (outputV in outputs) {
            val (rows, cols) = outputV.getSize()
            val matrix = Array(rows) { DoubleArray(cols) }
            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    matrix[i][j] = IO.nextInt().toDouble()
                }
            }
            outputV.calcBackward(matrix)
        }
        for (v in inputs) {
            printMatrix(v.dx)
        }
    }

    fun printMatrix(m: Array<DoubleArray>) {
        IO.println(m.joinToString("\n") { it.joinToString(" ") { "%.10f".format(it) } })
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