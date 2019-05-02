package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.OverflowException;

public class CheckedNegate extends UnaryOperation {

    CheckedNegate(TripleExpression left) {
        super(left);
    }

    public int eval(int l) throws OverflowException {
        if (l == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -l;
    }

}
