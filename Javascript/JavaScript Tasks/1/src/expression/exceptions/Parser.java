package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.ParserException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    TripleExpression parse(String expression) throws ParserException;
}
