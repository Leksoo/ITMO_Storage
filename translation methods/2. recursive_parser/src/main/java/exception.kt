class ParsingException(input: String, errorPos: Int, message: String = "") :
    RuntimeException("${input.dropLast(1)} <- unexpected symbol on position: $errorPos, $message")