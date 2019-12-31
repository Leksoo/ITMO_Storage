package parsers

import expression.*




object Parser {
    private var tr: TokenReader? = null

    fun parse(expression: String): Expression {
        tr = TokenReader(expression)
        val expr: Expression
        tr!!.getToken()
        expr = apply(false)
        return expr
    }


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
            Values.LAMBDA-> {
                tr!!.getToken()
                tr!!.getToken()
                left = Abstr(Variable(tr!!.variableToken), apply(true))
            }
            Values.LP -> {
                left = apply(true)
                tr!!.getToken()
            }
            Values.RP,Values.END -> {
                left = Wrapper(Variable("a"))
            }
            else -> throw IllegalArgumentException("ERROR PARSING")
        }
        return left
    }

    private fun apply(get: Boolean): Expression {
        var left = prim(get)
        while (true) {
                val tmp = prim(false)
                if(tmp is Wrapper){
                    return left
                }else{
                    left = Apply(left,tmp)
                }

        }
    }


}

