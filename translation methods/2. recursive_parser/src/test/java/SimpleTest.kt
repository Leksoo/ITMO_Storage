import java.io.FileWriter

fun main() {
    val fileWriter = FileWriter("output.txt")
    printTree(fileWriter,Parser().parse("(a or b) or (c and d xor not not not not a) or (not c) or d"))
    fileWriter.close()
}