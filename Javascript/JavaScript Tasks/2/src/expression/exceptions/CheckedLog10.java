package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.LogarithmException;

public class CheckedLog10 extends UnaryOperation {

    CheckedLog10(TripleExpression left) {
        super(left);
    }

    private int log10(int val) {
        int v = val;
        int log = 0;
        while (v >= 10) {
            log++;
            v /= 10;
        }
        return log;
    }

    public int eval(int l) throws LogarithmException {
        if (l <= 0) {
            throw new LogarithmException();
        }
        return log10(l);
    }

}

