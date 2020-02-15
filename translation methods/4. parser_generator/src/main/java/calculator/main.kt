package calculator

import generator.Generator

fun main() {
    Generator.generate("src/main/java/calculator/calc","src/main/java/calculator/")
    print(Parser(Lexer("src/main/java/calculator/input",true)).parse())
}
