package visitor

import tokenizer.Number
import tokenizer.Op
import tokenizer.Paren
import tokenizer.Token

interface TokenVisitor {
    fun visit(token: Op)
    fun visit(token: Paren)
    fun visit(token: Number)
    fun visit(tokens: List<Token>)

}