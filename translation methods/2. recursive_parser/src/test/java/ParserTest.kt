import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.lang.StringBuilder

class ParserTest {

    var parser = Parser()


    @Test
    fun testRandomComparedByString() {
        val next = mapOf(
            Pair("s", arrayOf("(", "v", "not")),
            Pair("v", arrayOf("or", "and", "xor", ")")),
            Pair("or", arrayOf("v", "(")),
            Pair("xor", arrayOf("v", "(")),
            Pair("and", arrayOf("v", "(")),
            Pair("not", arrayOf("v", "(", "not")),
            Pair("(", arrayOf("v", "not")),
            Pair(")", arrayOf("or", "and", "xor"))
        )

        fun getVar() = ('a'..'z').random()
        repeat(1000) {
            val input = StringBuilder()
            var last = "s"
            var size = 2000
            var balance = 0
            while (size != 0) {
                val cur = next.getValue(last).random()
                if (cur == "(") {
                    input.append(cur)
                    balance++
                } else if (cur == ")") {
                    if (balance == 0) {
                        continue
                    } else {
                        input.append(cur)
                        balance--
                    }
                } else if (cur == "v") {
                    input.append(getVar())
                } else {
                    input.append(cur)
                }
                input.append(" ")
                last = cur
                size--
            }
            when (last) {
                "or", "and", "not", "xor", "(" -> input.append(getVar()).append(" ")
            }
            repeat(balance) {
                input.append(")")
            }
            assertEquals(
                input.toString().replace(Regex("\\s"), ""),
                parser.parse(input.toString()).toString()
            )
        }
    }

    @TestFactory
    fun testIncorrectInputs(): Collection<DynamicTest> {
        val errorStrings = listOf(
            "a+b",
            "a----and-or----b",
            "a and b a",
            "a or and b",
            "a andor c",
            "ab or cd",
            "a not b",
            "a or B",
            "(a and b))",
            "((a or d)",
            "a or b (or c)",
            "(((()))()))))",
            "((()))",
            "(a xor (b xor (d or (h xor c)))",
            "and",
            "caorb",
            "c xor and",
            "c and )"
        )

        return errorStrings.map {
            dynamicTest(it) {
                assertThrows(ParsingException::class.java) { parser.parse(it) }
            }
        }.toList()
    }

    @TestFactory
    fun testComparedByString(): Collection<DynamicTest> {
        val validStrings = listOf(
            "a or b",
            "not d",
            "a or b or c and d",
            "a or (c or d) and v",
            "((((a))\n))",
            "c or ((((p xor x))))",
            "x xor    x",
            "x or x xor c and (b) or (d)",
            "not b\n and c",
            "c and not b",
            "a and not (a or c)",
            "not a or b and not d",
            "a\nor\tc\ror   not  \n\t\r  d",
            "a or a or a or a",
            "b and b and c and d",
            "a xor b xor c xor d",
            "not not not not not a"
        )

        return validStrings.map {
            dynamicTest(it) {
                assertEquals(it.replace(Regex("\\s"), ""), parser.parse(it).toString())

            }
        }.toList()
    }

    @Test
    fun testEmpty() {
        assertThrows(ParsingException::class.java) { parser.parse("") }
        assertThrows(ParsingException::class.java) { parser.parse("    ") }
    }

    @Test
    fun testComparedByTree() {
        assertEquals(Tree("V", arrayOf(Tree("a"))), parser.parse("a"))
        assertEquals(Tree("N", arrayOf(Tree("not"), Tree("V", arrayOf(Tree("a"))))), parser.parse("not a"))
        assertEquals(
            Tree(
                "E",
                arrayOf(
                    Tree("V", arrayOf(Tree("a"))),
                    Tree("or"),
                    Tree("V", arrayOf(Tree("b")))
                )
            ),
            parser.parse("a or b")
        )

        assertEquals(
            Tree(
                "E",
                arrayOf(
                    Tree("V", arrayOf(Tree("a"))),
                    Tree("or"),

                    Tree(
                        "E^",
                        arrayOf(
                            Tree("V", arrayOf(Tree("b"))),
                            Tree("or"),
                            Tree("V", arrayOf(Tree("c")))
                        )
                    )
                )
            ),
            parser.parse("a or b or c")
        )
    }
}