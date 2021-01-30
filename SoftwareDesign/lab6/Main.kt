import tokenizer.Number
import tokenizer.Op
import tokenizer.Paren
import tokenizer.Tokenizer
import visitor.CalcVisitor
import visitor.ParserVisitor
import visitor.PrintVisitor

fun main() {
    readLine()?.let { input ->
        try {
            val tokens = Tokenizer(input).process()
            val rpn = ParserVisitor().let {
                it.visit(tokens)
                it.result
            }
            println("expression in RPN:")
            PrintVisitor().visit(rpn)
            println()
            val result = CalcVisitor().let {
                it.visit(rpn)
                it.getResult()
            }
            println("final result:")
            println(result)
        } catch (e: Exception) {
            println("error happened while calculating")
            println(e.message)
        }
    } ?: println("input data is not provided")
}