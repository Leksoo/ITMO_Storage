package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.OverflowException;

public class CheckedPow10 extends UnaryOperation {

    CheckedPow10(TripleExpression left) {
        super(left);
    }

    private int pow10(int val) {
        int res = 1;
        for (int i = 1; i <= val; i++) {
            res *= 10;
        }
        return res;
    }

    public int eval(int l) throws OverflowException {
        if (l > 9 || l < 0) {
            throw new OverflowException();
        }
        return pow10(l);


    }

}
