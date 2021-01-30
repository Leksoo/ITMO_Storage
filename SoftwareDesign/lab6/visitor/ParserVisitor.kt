package visitor

import exception.ParserVisitorException
import tokenizer.Number
import tokenizer.Op
import tokenizer.Paren
import tokenizer.Token
import java.util.*
import kotlin.collections.ArrayList

class ParserVisitor : TokenVisitor {

    val result = ArrayList<Token>()
    private val stack = LinkedList<Token>()

    override fun visit(token: Op) {
        while (true) {
            val stackToken = stack.peekLast()
            if (stackToken is Op && (stackToken.priority >= token.priority)) {
                result.add(stackToken)
                stack.pollLast()
            } else {
                break
            }
        }
        stack.add(token)
    }

    override fun visit(token: Paren) {
        when (token) {
            Paren.Lparen -> stack.addLast(token)
            Paren.Rparen -> {
                poll@ while (true) {
                    when (val pollToken = stack.pollLast()) {
                        Paren.Lparen -> break@poll
                        null -> throw ParserVisitorException(token, "Parenthesises do not match")
                        else -> result.add(pollToken)
                    }
                }
            }
        }
    }

    override fun visit(token: Number) {
        result.add(token)
    }

    override fun visit(tokens: List<Token>) {
        for (token in tokens) {
            token.accept(this)
        }
        while (stack.isNotEmpty()) {
            if (stack.peekLast() !is Op) {
                throw ParserVisitorException(stack.peekLast(), "Parenthesises do not match")
            }
            result.add(stack.pollLast())
        }
    }

}