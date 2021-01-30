import exception.ParserVisitorException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import tokenizer.*
import tokenizer.Number
import visitor.ParserVisitor

class ParserVisitorTest {

    @Test
    fun `5`() {
        assert(listOf(Number(5)), listOf(Number(5)))
    }

    @Test
    fun `1+2`() {
        assert(
            listOf(Number(1), Op.Plus, Number(2)),
            listOf(Number(1), Number(2), Op.Plus)
        )
    }

    @Test
    fun `(1+2)*3`() {
        assert(
            listOf(
                Paren.Lparen, Number(1), Op.Plus, Number(2), Paren.Rparen, Op.Mul,
                Number(3)
            ), listOf(
                Number(1), Number(2), Op.Plus, Number(3), Op.Mul
            )
        )
    }

    @Test
    fun `5 div (-1)`() {
        assert(
            listOf(Number(5), Op.Div, Paren.Lparen, Number(-1), Paren.Rparen),
            listOf(Number(5), Number(-1), Op.Div)
        )
    }

    @Test
    fun `(1 + 2`() {
        assertThrows(ParserVisitorException::class.java) {
            ParserVisitor().let {
                it.visit(listOf(Paren.Lparen, Number(1), Op.Plus, Number(2)))
                it.result
            }
        }
    }

    @Test
    fun `(2))`() {
        assertThrows(ParserVisitorException::class.java) {
            ParserVisitor().let {
                it.visit(listOf(Paren.Lparen, Number(2), Paren.Rparen, Paren.Rparen))
                it.result
            }
        }
    }


    private fun assert(input: List<Token>, expected: List<Token>) {
        assertEquals(expected, ParserVisitor().let {
            it.visit(input)
            it.result
        })
    }
}