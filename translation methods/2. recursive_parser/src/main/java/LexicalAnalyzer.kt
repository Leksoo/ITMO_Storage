class LexicalAnalyzer(input: String) {

    private val input = "$input$"
    private var curPos: Int = -1
    var curChar: Char = nextChar()
    var curVariable: String = ""
        private set
    lateinit var curToken: Token
        private set


    private fun nextChar(): Char {
        curPos++
        try {
            curChar = input[curPos]
        } catch (e: IndexOutOfBoundsException) {
            throwError()
        }
        return curChar
    }

    fun nextToken(): Token {
        while (curChar.isWhitespace()) {
            nextChar()
        }
        when (curChar) {
            '(' -> {
                nextChar()
                curToken = Token.LPAREN
            }
            ')' -> {
                nextChar()
                curToken = Token.RPAREN
            }
            '$' -> {
                curToken = Token.END
            }
            in 'a'..'z' -> {
                if (checkThreeLetters()) {
                    repeat(3) {
                        nextChar()
                    }
                    return curToken
                }
                if (checkTwoLetters()) {
                    repeat(2) {
                        nextChar()
                    }
                    return curToken
                }
                curToken = Token.VAR
                curVariable = curChar.toString()
                nextChar()
            }
            else -> {
                throwError()
            }

        }
        return curToken
    }

    private fun checkThreeLetters(): Boolean {
        if (input.length > curPos + 2) {
            when (input.substring(curPos..curPos + 2)) {
                "and" -> {
                    curToken = Token.AND
                    return true
                }
                "xor" -> {
                    curToken = Token.XOR
                    return true
                }
                "not" -> {
                    curToken = Token.NOT
                    return true
                }
            }
        }
        return false
    }

    private fun checkTwoLetters(): Boolean {
        if (input.length > curPos + 1
            && input.substring(curPos..curPos + 1) == "or"
        ) {
            curToken = Token.OR
            return true
        }
        return false
    }

    fun throwError(message: String = "") {
        throw ParsingException(input, curPos, message)
    }


}