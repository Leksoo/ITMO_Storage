package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.OverflowException;

public class CheckedMultiply extends BinaryOperation {


    CheckedMultiply(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    public int eval(int l, int r) throws OverflowException {
        if (l > 0 && r > 0 && Integer.MAX_VALUE / l < r || r < 0 && l < 0 && Integer.MAX_VALUE / l > r) {
            throw new OverflowException();
        }
        if (l > 0 && r < 0 && Integer.MIN_VALUE / l > r || l < 0 && r > 0 && Integer.MIN_VALUE / r > l) {
            throw new OverflowException();
        }
        return l * r;
    }

}
