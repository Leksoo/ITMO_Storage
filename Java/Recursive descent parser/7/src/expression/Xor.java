package expression;

import expression.exceptions.BinaryOperation;

public class Xor extends BinaryOperation {

    public Xor(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    public int eval(int l, int r) {
        return l ^ r;
    }

    public double eval(double l, double r) {
        return (int) l ^ (int) r;
    }
}
