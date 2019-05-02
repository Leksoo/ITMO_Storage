package expression.exceptions.customExceptions;

public class LogarithmException extends EvalException {
    public LogarithmException() {
        super("logarithm of negative value");
    }
}