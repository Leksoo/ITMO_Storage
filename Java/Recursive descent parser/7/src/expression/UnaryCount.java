package expression;

import expression.exceptions.UnaryOperation;

public class UnaryCount extends UnaryOperation {

    public UnaryCount(TripleExpression left) {
        super(left);
    }

    public int eval(int l) {
        return Integer.bitCount(l);
    }

    public double eval(double l) {
        return Integer.bitCount((int) l);
    }
}
