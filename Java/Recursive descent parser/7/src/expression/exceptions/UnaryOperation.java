package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.customExceptions.EvalException;

public abstract class UnaryOperation implements TripleExpression {
    final TripleExpression left;

    protected UnaryOperation(TripleExpression left) {
        this.left = left;
    }


    @Override
    public int evaluate(int x, int y, int z) throws EvalException {
        return eval(left.evaluate(x, y, z));
    }

    protected abstract int eval(int l) throws EvalException;

}
