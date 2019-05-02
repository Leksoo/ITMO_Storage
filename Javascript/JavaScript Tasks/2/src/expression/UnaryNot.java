package expression;

import expression.exceptions.UnaryOperation;

public class UnaryNot extends UnaryOperation {

    public UnaryNot(TripleExpression left) {
        super(left);
    }

    public int eval(int l) {
        return ~l;
    }

    public double eval(double l) {
        return ~(int) l;
    }
}
