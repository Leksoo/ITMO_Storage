import gen.antlr.JavaClassLexer
import gen.antlr.JavaClassParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.*
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import java.io.FileReader
import java.lang.Exception
import kotlin.test.assertEquals


class Test {


    private fun parse(input: String): String {
        val lexer = JavaClassLexer(CharStreams.fromString(input))
        val tokens = CommonTokenStream(lexer)
        val parser = JavaClassParser(tokens)
        val classVisitor = ClassVisitor()
        return classVisitor.visit(parser.classDeclaration()).toString()
    }


    private fun getStrings(fileActual: Int, fileExpected: Int): Pair<String, String> {
            val readerExpected = FileReader("test${fileExpected}exp.txt")
            val readerActual = FileReader("test${fileActual}act.txt")
            val expected = readerExpected.readLines().joinToString("\n")
            val actual = readerActual.readLines().joinToString("\n")
            readerActual.close()
            readerActual.close()
            return Pair(expected, parse(actual))
    }

    @TestFactory
    fun tests(): Collection<DynamicTest> {
        val t = ArrayList<DynamicTest>()
        for (it in 1..4) {
            t.add(dynamicTest(it.toString()) {
                val (exp, act) = getStrings(it, it)
                assertEquals(exp, act)

            }
            )
        }
        return t
    }
}