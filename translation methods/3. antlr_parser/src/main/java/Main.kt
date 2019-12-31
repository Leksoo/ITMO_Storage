
import gen.antlr.JavaClassLexer
import gen.antlr.JavaClassParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileReader
import java.io.FileWriter

fun main() {
    val fileReader =FileReader("test.txt")
    val input = fileReader.readLines()
    fileReader.close()
    val lexer = JavaClassLexer(CharStreams.fromString(input.joinToString("\n")))
    val tokens = CommonTokenStream(lexer)
    val parser = JavaClassParser(tokens)
    val classVisitor = ClassVisitor()
    val result = classVisitor.visit(parser.classDeclaration())
    val fileWriter = FileWriter("out.txt")
    fileWriter.write(result.toString())
    fileWriter.close()
}