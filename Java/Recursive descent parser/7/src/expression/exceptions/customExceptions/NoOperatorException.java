package expression.exceptions.customExceptions;

public class NoOperatorException extends ParserException {
    public NoOperatorException(int index, String exp) {
        super("operation missed", index, exp);
    }
}
