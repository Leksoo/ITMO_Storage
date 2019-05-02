package expression.exceptions;

import expression.*;
import expression.exceptions.customExceptions.BracketsBalanceException;
import expression.exceptions.customExceptions.ParserException;
import expression.exceptions.customExceptions.WrongInputFormatException;


public class ExpressionParser implements Parser {
    private TokenReader tr;

    @Override
    public TripleExpression parse(String expression) throws ParserException {
        tr = new TokenReader(expression);
        TripleExpression triple;
        tr.getToken();
        triple = plusMinus(false);
        return triple;
    }


    private TripleExpression prim(boolean get) throws ParserException {
        TripleExpression left;
        if (get) {
            tr.getToken();
        }
        switch (tr.getCurrent()) {
            case NUMBER: {
                left = new Const(tr.getNumberToken());
                tr.getToken();
                break;
            }
            case VARIABLE: {
                left = new Variable(Character.toString(tr.getCurrentVariable()));
                tr.getToken();
                break;
            }
            case LOG10: {
                left = new CheckedLog10(prim(true));
                break;
            }
            case POW10: {
                left = new CheckedPow10(prim(true));
                break;
            }
            case MINUS: {
                left = new CheckedNegate(prim(true));
                break;
            }
            case LP: {
                left = plusMinus(true);
                if (tr.getCurrent() != Values.RP) {
                    throw new BracketsBalanceException(tr.getIndex(), tr.getExpression());
                }
                tr.getToken();
                break;
            }
            default:
                throw new WrongInputFormatException(tr.getIndex(), tr.getExpression());
        }
        return left;
    }


    private TripleExpression mulDiv(boolean get) throws ParserException {
        TripleExpression left = prim(get);
        while (true) {
            switch (tr.getCurrent()) {
                case MUL:
                    left = new CheckedMultiply(left, prim(true));
                    break;
                case DIV:
                    left = new CheckedDivide(left, prim(true));
                    break;
                default:
                    return left;
            }
        }

    }

    private TripleExpression plusMinus(boolean get) throws ParserException {
        TripleExpression left = mulDiv(get);
        while (true) {
            switch (tr.getCurrent()) {
                case PLUS:
                    left = new CheckedAdd(left, mulDiv(true));
                    break;
                case SUB:
                    left = new CheckedSubtract(left, mulDiv(true));
                    break;
                default:
                    return left;
            }
        }
    }


}
