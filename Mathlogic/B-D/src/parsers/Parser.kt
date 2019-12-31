package parsers

import exceptions.ParserException
import expression.*




object Parser {
    private var tr: TokenReader? = null

    @Throws(ParserException::class)
    fun parse(expression: String): Expression {
        tr = TokenReader(expression)
        val expr: Expression
        tr!!.getToken()
        expr = impl(false)
        return expr
    }


    @Throws(ParserException::class)
    private fun prim(get: Boolean): Expression {
        val left: Expression
        if (get) {
            tr!!.getToken()
        }
        when (tr!!.current) {
            Values.VARIABLE -> {
                left = Variable(tr!!.variableToken)
                tr!!.getToken()
            }
            Values.NOT-> {
                left = Not(prim(true))
            }
            Values.LP -> {
                left = impl(true)
                tr!!.getToken()
            }
            else -> throw ParserException("ERROR PARSING")
        }
        return left
    }


    @Throws(ParserException::class)
    private fun and(get: Boolean): Expression {
        var left = prim(get)
        while (true) {
            if (tr!!.current == Values.AND) {
                left = And(left, prim(true))
            }
            else{
                return left
            }
        }

    }

    @Throws(ParserException::class)
    private fun or(get: Boolean): Expression {
        var left = and(get)
        while (true) {
            if (tr!!.current == Values.OR) {
                left = Or(left, and(true))
            }
            else{
                return left
            }
        }
    }

    @Throws(ParserException::class)
    private fun impl(get: Boolean): Expression {
        val left = or(get)
        while (true) {
            return if (tr!!.current == Values.IMPL) {
                Impl(left, impl(true))
            } else{
                left
            }
        }
    }

}

