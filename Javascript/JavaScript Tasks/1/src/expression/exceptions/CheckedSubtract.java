package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.OverflowException;

public class CheckedSubtract extends BinaryOperation {

    CheckedSubtract(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    public int eval(int l, int r) throws OverflowException {
        if (l >= 0 && r < 0 && l > r + Integer.MAX_VALUE || l <= 0 && r > 0 && l < r + Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return l - r;
    }

}
