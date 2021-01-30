package tokenizer

import visitor.TokenVisitor

interface IToken {
    fun accept(visitor: TokenVisitor)
}

sealed class Token(
    private val stringValue: String
) : IToken {

    override fun toString(): String {
        return stringValue
    }
}

sealed class Op(value: String) : Token(value) {
    abstract val priority: Int
    abstract fun apply(a: Number, b: Number): Number

    object Plus : Op("+") {
        override val priority = 0

        override fun apply(a: Number, b: Number): Number {
            return Number(a.v + b.v)
        }
    }

    object Minus : Op("-") {
        override val priority = 0

        override fun apply(a: Number, b: Number): Number {
            return Number(a.v - b.v)
        }
    }

    object Div : Op("/") {
        override val priority = 1

        override fun apply(a: Number, b: Number): Number {
            return Number(a.v / b.v)
        }
    }

    object Mul : Op("*") {
        override val priority = 1

        override fun apply(a: Number, b: Number): Number {
            return Number(a.v * b.v)
        }
    }


    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}

sealed class Paren(value: String) : Token(value) {
    object Lparen : Paren("(")
    object Rparen : Paren(")")

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}

data class Number(val v: Int) : Token("$v") {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return super.toString()
    }
}