package visitor

import exception.CalcVisitorException
import exception.CalcVisitorResultException
import tokenizer.Number
import tokenizer.Op
import tokenizer.Paren
import tokenizer.Token
import java.util.*

class CalcVisitor : TokenVisitor {

    private val stack = LinkedList<Token>()

    fun getResult(): Int {
        val res = stack.pollLast()
        if (res !is Number || stack.isNotEmpty()) {
            throw CalcVisitorResultException(res, "result token is not a number")
        }
        return res.v
    }

    override fun visit(token: Op) {
        val b = stack.pollLast()
        val a = stack.pollLast()
        if (a !is Number || b !is Number) {
            throw CalcVisitorException(token, "Not enough operands to apply operation")
        }
        stack.addLast(token.apply(a, b))
    }

    override fun visit(token: Paren) {
        throw CalcVisitorException(token, "In RPN should be no parentheses")
    }

    override fun visit(token: Number) {
        stack.addLast(token)
    }

    override fun visit(tokens: List<Token>) {
        for (token in tokens) {
            token.accept(this)
        }
    }

}