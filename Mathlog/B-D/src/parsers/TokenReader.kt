package parsers

import exceptions.ParserException



 class TokenReader(val expression: String) {
     var index: Int
     var variableToken: String
     var current: Values

     init {
         index = 0
         variableToken = ""
         current = Values.START
     }


    @Throws(ParserException::class)
    private fun readVariable(): String {
        val variable = StringBuilder()
        if (index < expression.length && Character.isLetter(expression[index])) {
            variable.append(expression[index])
            index++
        }
        while (index < expression.length && (Character.isLetter(expression[index])
                    || Character.isDigit(expression[index]) || expression[index] == '\'' || expression[index] == '’')
        ) {
            variable.append(expression[index])
            index++
        }
        index--
        return variable.toString()
    }

    private fun whiteSpaceClearing() {
        while (index < expression.length && Character.isWhitespace(expression[index])) {
            index++
        }
    }


    @Throws(ParserException::class)
    fun getToken() {
        whiteSpaceClearing()
        if (index >= expression.length) {
            current = Values.END
            return
        }
        when (expression[index]) {
            '&' -> {
                current = Values.AND
            }
            '|' -> {
                current = Values.OR
            }
            '!' -> {
                current = Values.NOT
            }
            '(' -> {
                current = Values.LP
            }
            ')' -> {

                current = Values.RP

            }
            '-' -> {
                current = Values.IMPL
                index++
            }
            else -> {
                // number
                variableToken = readVariable()
                current = Values.VARIABLE
            }
        }
        index++

    }
}