import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.ln

object F {
    val fileName = ""
    //================================================================================

    class Obj(
        val C: Int,
        val words: List<String>,
        val wordsCount: Map<String, Int>
    )

    fun run() {
        val K = IO.nextInt()
        val penalties = ArrayList<Int>()
        for (i in 1..K) {
            penalties.add(IO.nextInt())
        }
        val alpha = IO.nextInt()
        val N = IO.nextInt()
        val allWords = HashSet<String>()
        val classObjs = List(K) { ArrayList<Obj>() }

        for (i in 0 until N) {
            val words = ArrayList<String>()
            val wordsCount = HashMap<String, Int>()
            val C = IO.nextInt() - 1
            val L = IO.nextInt()
            for (j in 1..L) {
                words.add(IO.next())
                wordsCount.putIfAbsent(words.last(), 0)
                wordsCount[words.last()] = wordsCount[words.last()]!! + 1
            }
            allWords.addAll(words)
            classObjs[C].add(Obj(C, words, wordsCount))
        }

        val pWordInClass = List(K) { HashMap<String, Double>() }
        for (clas in 0 until K) {
            val wordsInClass = HashSet<String>()
            for (obj in classObjs[clas]) {
                for ((k, v) in obj.wordsCount) {
                    pWordInClass[clas].putIfAbsent(k, 0.0)
                    pWordInClass[clas][k] = pWordInClass[clas][k]!! + 1
                    wordsInClass.add(k)
                }
            }
            for (word in allWords) {
                pWordInClass[clas][word] = ((pWordInClass[clas][word] ?: 0.0) + alpha) /
                        (classObjs[clas].size + alpha * 2.0)
            }
        }
        val classP = MutableList(K) { 0.0 }
        for (i in 0 until K) {
            classP[i] = classObjs[i].size.toDouble() / N
        }
        val M = IO.nextInt()
        for (i in 0 until M) {
            val words = HashSet<String>()
            val L = IO.nextInt()
            for (j in 1..L) {
                words.add(IO.next())
            }
            var ps = MutableList(K) { 0.0 }
            var c = 0
            for (clas in 0 until K) {
                if (classObjs[clas].size == 0) continue
                var p = penalties[clas].toDouble() * classP[clas]
                for (word in allWords) {
                    if (word in words) {
                        p *= pWordInClass[clas][word]!!
                    } else {
                        p *= 1.0 - pWordInClass[clas][word]!!
                    }
                }
                c++
                ps[clas] = p
            }
            val a = ps.sum() / c
            ps = ps.map{it + a}.toMutableList()
            for(clas in 0 until K) {
                print("%.15f ".format(ps[clas] / ps.sum()))
            }
            println()
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