package tokenizer

import exception.TokenizerException
import java.lang.StringBuilder

class Tokenizer(val input: String) {

    lateinit var state: IState
    lateinit var tokens: ArrayList<Token>

    fun process(): List<Token> {
        tokens = ArrayList()
        state = ReadState()
        for (i in input.indices) {
            state.handle(i)
        }
        state.finish()
        return tokens
    }

    interface IState {
        fun handle(pos: Int)
        fun finish()
    }

    inner class ReadState : IState {
        override fun handle(pos: Int) {
            when (input[pos]) {
                in '0'..'9' -> {
                    state = NumberState()
                    state.handle(pos)
                }
                '(' -> tokens.add(Paren.Lparen)
                ')' -> tokens.add(Paren.Rparen)
                '+' -> tokens.add(Op.Plus)
                '/' -> tokens.add(Op.Div)
                '*' -> tokens.add(Op.Mul)
                '-' -> {
                    if (tokens.isNotEmpty() && tokens.last() is Number) {
                        tokens.add(Op.Minus)
                    } else {
                        state = NumberState()
                        state.handle(pos)
                    }
                }
                else -> {
                    if (!input[pos].isWhitespace()) {
                        state = ErrorState()
                        state.handle(pos)
                    }
                }
            }

        }

        override fun finish() {
        }

    }

    inner class ErrorState : IState {
        override fun handle(pos: Int) {
            throw TokenizerException(input, pos)
        }

        override fun finish() {

        }
    }

    inner class NumberState : IState {
        private val numberSb = StringBuilder()

        override fun handle(pos: Int) {
            input[pos].let {
                if (it == '-' && numberSb.isEmpty() || it in '0'..'9') {
                    numberSb.append(it)
                } else {
                    tokens.add(Number(numberSb.toString().toInt()))
                    state = ReadState()
                    state.handle(pos)
                }
            }
        }

        override fun finish() {
            tokens.add(Number(numberSb.toString().toInt()))
        }
    }

}