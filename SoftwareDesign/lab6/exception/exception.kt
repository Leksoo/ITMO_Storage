package exception

import tokenizer.Token

class TokenizerException(input: String, errorPos: Int) :
    RuntimeException(
        "${input.take(errorPos)}__${input[errorPos]}__${input.drop(errorPos + 1)}" +
                " <- unexpected symbol on position: $errorPos"
    )

class ParserVisitorException(token: Token, message: String) :
    RuntimeException(
        "unexpected token $token, $message"
    )

class CalcVisitorException(token: Token, message: String) :
    RuntimeException(
        "unexpected token $token, $message"
    )

class CalcVisitorResultException(token: Token?, message: String) :
    RuntimeException(
        "unexpected result value = $token, $message"
    )