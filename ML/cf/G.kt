import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

object G {
    val fileName = ""
    //================================================================================

    class Obj(
        var x: ArrayList<Int>,
        var y: Int
    )

    class Node(
        val id: Int,
        val y: Int,
        val leaf: Boolean,
        val splitValue: Double = 0.0,
        val splitInd: Int = 0,
        var left: Node? = null,
        var right: Node? = null
    )

    class GiniRes(
        val left: List<Obj>,
        val right: List<Obj>,
        val splitValue: Double,
        val splitInd: Int
    )

    var treeInd = 1
    val treeList = ArrayList<Node>()

    fun build(objs: List<Obj>, depth: Int): Node {
        terminalIfOneClass(objs)?.let {
            treeList.add(it)
            return it
        }

        val giniRes = splitTree(objs)
        var node: Node
        if (giniRes.left.isEmpty() || giniRes.right.isEmpty()) {
            node = terminal(giniRes.left, giniRes.right)
        } else if (depth >= H) {
            node = terminal(giniRes.left, giniRes.right)
        } else {
            val ind = treeInd++
            val left = build(giniRes.left, depth + 1)
            val right = build(giniRes.right, depth + 1)
            node = Node(ind, 0, false, giniRes.splitValue, giniRes.splitInd, left, right)
        }
        treeList.add(node)
        return node

    }

    private fun splitTree(o: List<Obj>): GiniRes {
        var bestGini = 10000000000000000000.0
        var bestXInd = 0
        var splitValue = 0.0
        for (xInd in 0 until M) {
            val objs = o.sortedByDescending { it.x[xInd] }
            val leftClassCount = MutableList(K) { 0 }
            val rightClassCount = MutableList(K) { 0 }
            val leftClassVals = MutableList(K) { 0.0 }
            val rightClassVals = MutableList(K) { 0.0 }
            for (obj in objs) {
                leftClassCount[obj.y]++
            }
            for (cl in 0 until K) {
                leftClassVals[cl] = (1.0 * leftClassCount[cl] / objs.size).pow(2)
            }
            for (i in -1 until objs.size) {
                if (i != -1) {
                    val obj = objs[i]
                    leftClassCount[obj.y]--
                    for (j in leftClassVals.indices) {
                        leftClassVals[j] = (1.0 * leftClassCount[j] / (objs.size - (i + 1))).pow(2)
                    }
                    rightClassCount[obj.y]++
                    for (j in rightClassVals.indices) {
                        rightClassVals[j] = (1.0 * rightClassCount[j] / (i + 1)).pow(2)
                    }
                }
                val giniLeft = 1.0 - leftClassVals.sum()
                val giniRight = 1.0 - rightClassVals.sum()
                val resGini =
                    (objs.size - (i + 1)).toDouble() / objs.size * giniLeft + (i + 1).toDouble() / objs.size * giniRight
                if (resGini < bestGini) {
                    bestGini = resGini
                    bestXInd = xInd
                    splitValue = if (i == -1) {
                        Int.MAX_VALUE.toDouble()
                    } else if (i == objs.size - 1) {
                        Int.MIN_VALUE.toDouble()
                    } else {
                        (objs[i].x[xInd].toDouble() + objs[i + 1].x[xInd].toDouble()) / 2.0
                    }
                   // splitValue = if (i < 0) Int.MAX_VALUE.toDouble() else objs[i].x[xInd].toDouble()
                }
            }
        }

        val left = ArrayList<Obj>()
        val right = ArrayList<Obj>()

        for (obj in o) {
            if (obj.x[bestXInd] < splitValue) {
                left.add(obj)
            } else {
                right.add(obj)
            }
        }
        return GiniRes(left, right, splitValue, bestXInd)
    }

    fun terminalIfOneClass(objs: List<Obj>): Node? {
        if (objs.map { it.y }.toHashSet().size == 1) {
            return Node(treeInd++, objs[0].y, true)
        }
        return null
    }

    fun terminal(objs: List<Obj>): Node {
        return terminal(objs, emptyList())
    }

    fun terminal(left: List<Obj>, right: List<Obj>): Node {
        val classCount = MutableList(K) { 0 }
        for (obj in left) {
            classCount[obj.y]++
        }
        for (obj in right) {
            classCount[obj.y]++
        }
        var maxCount = 0
        var maxCountClass = 0
        for (i in classCount.indices) {
            if (classCount[i] > maxCount) {
                maxCount = classCount[i]
                maxCountClass = i
            }
        }
        return Node(treeInd++, maxCountClass, true)
    }


    var M: Int = 0
    var K: Int = 0
    var H: Int = 0
    var N: Int = 0

    fun run() {
        M = IO.nextInt()
        K = IO.nextInt()
        H = IO.nextInt()
        N = IO.nextInt()
        val objs = ArrayList<Obj>()
        for (i in 0 until N) {
            val x = ArrayList<Int>()
            for (j in 0 until M) {
                x.add(IO.nextInt())
            }
            objs.add(Obj(x, IO.nextInt() - 1))
        }

        build(objs, 0)
        IO.println(treeList.size)
        treeList.sortedBy { it.id }.forEach {
            if (it.leaf) {
                IO.println("C ${it.y + 1}")
            } else {
                IO.println("Q ${it.splitInd + 1} ${"%.1f".format(it.splitValue)} ${it.left!!.id} ${it.right!!.id}")
            }
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