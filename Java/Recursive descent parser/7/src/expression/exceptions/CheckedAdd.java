package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.OverflowException;

public class CheckedAdd extends BinaryOperation {

    CheckedAdd(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    public int eval(int l, int r) throws OverflowException {
        if (l > 0 && Integer.MAX_VALUE - l < r || l < 0 && Integer.MIN_VALUE - l > r) {
            throw new OverflowException();
        }
        return l + r;
    }

}
