package visitor

import tokenizer.Number
import tokenizer.Op
import tokenizer.Paren
import tokenizer.Token

class PrintVisitor : TokenVisitor {
    override fun visit(token: Op) {
        print(token)
    }

    override fun visit(token: Paren) {
        print(token)
    }

    override fun visit(token: Number) {
        print(token)
    }

    override fun visit(tokens: List<Token>) {
        for (token in tokens) {
            token.accept(this)
            print(" ")
        }
    }

}