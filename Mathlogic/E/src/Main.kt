import processor.ValidationProcessor
import java.io.*
import java.util.*
import java.io.BufferedOutputStream
import java.lang.Exception


object Main {


    //private val inp = BufferedReader(FileReader("tests/test.txt"))
    val inp = BufferedReader(InputStreamReader(System.`in`))
    val out = BufferedOutputStream(System.out)

    private fun readLine() = inp.readLine()
    //

    @JvmStatic
    fun main(args: Array<String>) {
        val splittedStatement =
            parseStatement(readLine())
        val proofLines = ArrayList<String>()
        var line = readLine()
        while (line != null && line != "ex") {
            proofLines.add(line)
            line = readLine()
        }
        println(
            ValidationProcessor().validate(
                Axioms.axiomList,
                splittedStatement.first, proofLines, splittedStatement.second
            )
        )
        out.close()

    }

    private fun parseStatement(statement: String): Pair<List<String>, String> {
        val splitByTurnstile = statement.split("|-")
        return Pair(
            if (splitByTurnstile[0] == "") emptyList() else splitHypotheses(splitByTurnstile[0]),
            splitByTurnstile[1]
        )
    }

    fun splitHypotheses(str: String): List<String> {
        val hypotheses = ArrayList<String>()
        var index = 0
        var balance = 0
        val expr = StringBuilder()
        while (index < str.length) {
            val ch = str[index]
            if (ch == ',') {
                if (balance == 0) {
                    hypotheses.add(expr.toString())
                    expr.clear()
                } else {
                    expr.append(ch)
                }
            } else {
                if (ch == '(') balance--
                else if (ch == ')') balance++
                expr.append(ch)
            }
            index++
        }
        hypotheses.add(expr.toString())
        return hypotheses
    }


}