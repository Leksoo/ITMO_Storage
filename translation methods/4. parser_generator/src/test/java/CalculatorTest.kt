import calculator.Lexer
import calculator.Parser
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class CalculatorTest {

    @TestFactory
    fun test(): Collection<DynamicTest> {
        val validStrings = mapOf<String, Int>(
            Pair("99-98", 1),
            Pair("10*5-50+25", 25),
            Pair("0+0" , 0),
            Pair("222    \n\n\n - 111" , 111),
            Pair("(((1))) + (((2)))" , 3),
            Pair("(1*1*1*1)*1*(1*1)" , 1)

            )
        return validStrings.map {
            dynamicTest(it.key) {
                assertEquals(Parser(Lexer(it.key, false)).parse(), it.value)

            }
        }.toList()
    }
}