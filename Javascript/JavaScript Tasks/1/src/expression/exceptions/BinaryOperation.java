package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.EvalException;

public abstract class BinaryOperation implements TripleExpression {
    final TripleExpression left;
    final TripleExpression right;

    protected BinaryOperation(TripleExpression left, TripleExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(int x, int y, int z) throws EvalException {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);
        return eval(l, r);
    }

    public abstract int eval(int l, int r) throws EvalException;


}
