import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import tokenizer.Tokenizer
import visitor.CalcVisitor
import visitor.ParserVisitor

class CrossVisitorsTest {

    @Test
    fun `1+2`() {
        assert("1  +2", 3)
    }

    @Test
    fun `(1+2)*3`() {
        assert("( 0+ 1+2)*6/3", 6)
    }

    @Test
    fun `-1+10`() {
        assert("-1 +10-1", 8)
    }

    @Test
    fun `5 div (-1)`() {
        assert("5+5/((-1)) ", 0)
    }

    @Test
    fun `1--1`() {
        assert("1--1 ", 2)

    }

    private fun assert(input: String, expected: Int) {
        Assertions.assertEquals(expected,
            CalcVisitor().let {calc ->
                calc.visit(ParserVisitor().let { parse ->
                    parse.visit(Tokenizer(input).process())
                    parse.result
                })
                calc.getResult()
            }
        )
    }
}