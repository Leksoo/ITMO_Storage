package bool_expressions


import bool_expression.Lexer
import bool_expression.Parser
import generator.Generator
import printTree
import java.io.FileWriter

fun main() {
    Generator.generate("src/main/java/bool_expressions/bool", "src/main/java/bool_expressions/")
    FileWriter("src/main/java/bool_expressions/output.txt").use {
        printTree(
            it,
            Parser(Lexer("src/main/java/bool_expressions/input", true)).parse()
        )
    }
}