import exception.CalcVisitorException
import exception.CalcVisitorResultException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import tokenizer.Number
import tokenizer.Op
import tokenizer.Token
import visitor.CalcVisitor
import visitor.ParserVisitor

class CalcVisitorTest {

    @Test
    fun `5`() {
        assert(listOf(Number(5)), 5)
    }

    @Test
    fun `1 2 +`() {
        assert(
            listOf(Number(1), Number(2), Op.Plus),
            3
        )
    }

    @Test
    fun `1 2 + 3 *`() {
        assert(
            listOf(
                Number(1), Number(2), Op.Plus, Number(3), Op.Mul
            ), 9
        )
    }

    @Test
    fun `5 -1 div`() {
        assert(
            listOf(Number(5), Number(-1), Op.Div),
            -5
        )
    }

    @Test
    fun `1 +`() {
        Assertions.assertThrows(CalcVisitorException::class.java) {
            CalcVisitor().let {
                it.visit(listOf(Number(1), Op.Plus))
                it.getResult()
            }
        }
    }

    @Test
    fun `1 2`() {
        Assertions.assertThrows(CalcVisitorResultException::class.java) {
            CalcVisitor().let {
                it.visit(listOf(Number(1), Number(2)))
                it.getResult()
            }
        }
    }

    private fun assert(input: List<Token>, expected: Int) {
        Assertions.assertEquals(expected, CalcVisitor().let {
            it.visit(input)
            it.getResult()
        })
    }
}