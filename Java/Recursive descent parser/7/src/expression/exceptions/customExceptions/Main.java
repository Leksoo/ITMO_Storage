package expression.exceptions.customExceptions;

import expression.exceptions.ExpressionParser;

public class Main {
    public static void main(String[] args) throws ParserException, EvalException {
        System.out.println(new ExpressionParser().parse("pow10(2:3)").evaluate(0, 2, 3));
    }
}
