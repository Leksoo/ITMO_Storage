package generator

const val START_RULE_ERROR = "No rules declared for starting point"
const val END_INPUT_ERROR = "Expecting end of input, but found "


class LexerException (
    message : String
) : Exception(message)

class ParserException (
    message : String
) : Exception(message)

class GeneratorException (
    message : String
) : Exception(message)


fun parserError(message: String) {
    throw ParserException(message)
}