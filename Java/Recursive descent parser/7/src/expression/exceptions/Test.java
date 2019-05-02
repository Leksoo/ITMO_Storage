package expression.exceptions;

import expression.exceptions.customExceptions.ParserException;

public class Test {
    public static void main(String[] args) throws ParserException {
        ExpressionParser parser = new ExpressionParser();
        parser.parse("2-y");
    }
}
