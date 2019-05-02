package expression.exceptions.customExceptions;

public class DivisionByZeroException extends EvalException {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
