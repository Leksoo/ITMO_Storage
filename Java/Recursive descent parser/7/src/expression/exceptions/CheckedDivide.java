package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.DivisionByZeroException;
import expression.exceptions.customExceptions.OverflowException;

public class CheckedDivide extends BinaryOperation {

    CheckedDivide(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    public int eval(int l, int r) throws OverflowException, DivisionByZeroException {
        if (r == 0) {
            throw new DivisionByZeroException();
        }
        if (l == Integer.MIN_VALUE && r == -1) {
            throw new OverflowException();
        }
        return l / r;
    }

}
