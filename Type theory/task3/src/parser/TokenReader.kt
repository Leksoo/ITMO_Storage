package parsers


 class TokenReader(val expression: String) {
     var index: Int
     var variableToken: String
     var current: Values

     init {
         index = 0
         variableToken = ""
         current = Values.START
     }

     fun readVariable(): String {
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


    fun getToken() {
        whiteSpaceClearing()
        if (index >= expression.length) {
            current = Values.END
            return
        }
        when (expression[index]) {
            '\\' -> {
                current = Values.LAMBDA
            }
            '.' -> {
                current = Values.DOT
            }
            '(' -> {
                current = Values.LP
            }
            ')' -> {

                current = Values.RP

            }
            else -> {
                variableToken = readVariable()
                current = Values.VARIABLE
            }
        }
        index++

    }
}