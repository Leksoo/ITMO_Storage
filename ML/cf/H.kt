import javafx.scene.text.FontWeight
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.random.Random

object H {
    val fileName = ""

    //================================================================================

    fun randomW(): Double {
        var a = Random.nextDouble(-1.0, 1.0)
        while (a == 0.0) {
            a = Random.nextDouble(-1.0, 1.0)
        }
        return a
    }

    class Network(
        val layers: MutableList<Layer> = ArrayList()
    ) {
        fun forward(values: List<Int>): Int = layers.first().forward(values)

        fun addLayer(neuronCount: Int, first: Boolean = false) {
            val neurons = ArrayList<Neuron>()
            for (i in 0 until neuronCount) {
                val weights = ArrayList<Double>()
                val wCount = if (first) {
                    neuronCount
                } else {
                    layers.last().neurons.size
                }
                for (j in 0 until wCount) {
                    weights.add(randomW())
                }
                neurons.add(Neuron(weights, randomW()))
            }
            if (first) {
                layers.add(Layer(neurons, null, null))
            } else {
                val l = Layer(neurons, layers.last(), null)
                layers.last().nextLayer = l
                layers.add(l)
            }
        }
    }

    open class Layer(
        val neurons: List<Neuron>,
        var prevLayer: Layer? = null,
        var nextLayer: Layer? = null
    ) {

        fun forward(values: List<Int>): Int {
            val newValues = ArrayList<Int>()
            for (n in neurons) {
                newValues.add(n.calcValue(values))
            }
            if (nextLayer == null) {
                return newValues[0]
            }
            return nextLayer!!.forward(newValues)
        }
    }

    class Neuron(
        val weights: MutableList<Double>,
        val shiftW: Double = 0.0
    ) {
        fun calcValue(prevs: List<Int>): Int {
            var res = 0.0
            for (i in prevs.indices) {
                res += weights[i] * prevs[i]
            }
            res += shiftW
            if (res == 0.0) throw ArithmeticException()
            return if (res > 0.0) 1 else 0
        }
    }

    var M = 0
    fun run() {
        M = IO.nextInt()
        var bestErr = 100.0
        var bestNet: Network? = null
        val fValues = ArrayList<Int>()
        for (i in 0 until 2.0.pow(M).toInt()) {
            fValues.add(IO.nextInt())
        }
        repeat(100000) {
            val network = Network()
            network.addLayer(M, true)
            network.addLayer(1)
            var err = 0.0
            for (i in 0 until 2.0.pow(M).toInt()) {
                var x = i.toString(2)
                while (x.length != M) {
                    x = "0$x"
                }
                val values = x.reversed().map { it.toInt() }
                val res = network.forward(values)
                err += (fValues[i] - res) * (fValues[i] - res)
            }
            err /= 2.0.pow(M).toInt()
            if (err < bestErr) {
                bestErr = err
                bestNet = network
            }
        }
        //IO.println("ERR = ${bestErr}")
        IO.println(2)
        for(layer in bestNet!!.layers) {
            IO.print("${layer.neurons.size} ")
        }
        IO.println("")
        for(layer in bestNet!!.layers) {
            for(n in layer.neurons) {
                for(w in n.weights) {
                    IO.print("%.10f ".format(w))
                }
                IO.print("%.10f".format(n.shiftW))
                IO.println("")
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