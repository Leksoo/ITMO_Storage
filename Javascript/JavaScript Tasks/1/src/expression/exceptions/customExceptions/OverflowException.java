package expression.exceptions.customExceptions;

public class OverflowException extends EvalException {
    public OverflowException() {
        super("overflow");
    }
}