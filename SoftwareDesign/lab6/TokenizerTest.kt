import exception.TokenizerException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import tokenizer.*
import tokenizer.Number

class TokenizerTest {

    @Test
    fun `5`() {
        assert("5", listOf(Number(5)))
    }

    @Test
    fun `1+2`() {
        assert("1  +2", listOf(Number(1), Op.Plus, Number(2)))
    }

    @Test
    fun `(1+2)*3`() {
        assert(
            "(  1+2)*3", listOf(
                Paren.Lparen, Number(1), Op.Plus, Number(2), Paren.Rparen, Op.Mul,
                Number(3)
            )
        )
    }

    @Test
    fun `-1+10`() {
        assert("-1 +10", listOf(Number(-1), Op.Plus, Number(10)))
    }

    @Test
    fun `5 div (-1)`() {
        assert("5/(-1) ", listOf(Number(5), Op.Div, Paren.Lparen, Number(-1), Paren.Rparen))
    }

    @Test
    fun `5a5`() {
        assertThrows(TokenizerException::class.java) { Tokenizer("5a5").process() }
    }

    @Test
    fun `1--1`() {
        assert("1--1 ", listOf(Number(1), Op.Minus, Number(-1)))

    }

    private fun assert(str: String, tokens: List<Token>) {
        assertEquals(tokens, Tokenizer(str).process())
    }

}