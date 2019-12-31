class Parser {
    lateinit var lex: LexicalAnalyzer

    fun parse(input: String): Tree {
        lex = LexicalAnalyzer(input)
        lex.nextToken()
        val res = e()
        if (res == null || lex.curToken != Token.END) {
            lex.throwError()
        }
        return res!!
    }

    private fun e(): Tree? {
        when (lex.curToken) {
            Token.NOT, Token.VAR, Token.LPAREN -> {
                val x = x()!!
                val ePrime = ePrime() ?: return x
                return Tree("E", arrayOf(x, Tree("or"), ePrime))
            }
            else -> {
                lex.throwError("expected: not, variable, (")
            }
        }
        return null
    }

    private fun ePrime(): Tree? {
        when (lex.curToken) {
            Token.OR -> {
                lex.nextToken()
                val x = x()!!
                val ePrime = ePrime() ?: return x
                return Tree("E^", arrayOf(x, Tree("or"), ePrime))
            }
            else -> {
                return null
            }
        }
    }

    private fun x(): Tree? {
        when (lex.curToken) {
            Token.NOT, Token.VAR, Token.LPAREN -> {
                val a = a()!!
                val xPrime = xPrime() ?: return a
                return Tree("X", arrayOf(a, Tree("xor"), xPrime))
            }
            else -> {
                lex.throwError("expected: not, variable, (")
            }
        }
        return null
    }

    private fun xPrime(): Tree? {
        when (lex.curToken) {
            Token.XOR -> {
                lex.nextToken()
                val a = a()!!
                val xPrime = xPrime() ?: return a
                return Tree("X^", arrayOf(a, Tree("xor"), xPrime))
            }
            else -> {
                return null
            }
        }
    }

    private fun a(): Tree? {
        when (lex.curToken) {
            Token.NOT, Token.VAR, Token.LPAREN -> {
                val n = n()!!
                val aPrime = aPrime() ?: return n
                return Tree("A", arrayOf(n, Tree("and"), aPrime))
            }
            else -> {
                lex.throwError("expected: not, variable, (")
            }
        }
        return null
    }

    private fun aPrime(): Tree? {
        when (lex.curToken) {
            Token.AND -> {
                lex.nextToken()
                val n = n()!!
                val aPrime = aPrime() ?: return n
                return Tree("A^", arrayOf(n, Tree("and"), aPrime))
            }
            else -> {
                return null
            }
        }
    }

    private fun n(): Tree? {
        when (lex.curToken) {
            Token.VAR, Token.LPAREN -> {
                return v()
            }
            Token.NOT -> {
                lex.nextToken()
                val n = n()!!
                return Tree("N", arrayOf(Tree("not"), n))
            }
            else -> {
                lex.throwError("expected: not, variable, (")
            }
        }
        return null
    }

    private fun v(): Tree? {
        when (lex.curToken) {
            Token.VAR -> {
                val variable = lex.curVariable
                lex.nextToken()
                return Tree("V", arrayOf(Tree(variable)))
            }
            Token.LPAREN -> {
                lex.nextToken()
                val e = e()!!
                if (lex.curToken != Token.RPAREN) {
                    lex.throwError("expected: )")
                }
                lex.nextToken()
                return Tree("V", arrayOf(Tree("("), e, Tree(")")))
            }
            else -> {
                lex.throwError("expected: variable, (")
            }
        }
        return null
    }

}