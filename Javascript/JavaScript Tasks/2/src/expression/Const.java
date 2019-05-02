package expression;

public class Const implements TripleExpression {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}
