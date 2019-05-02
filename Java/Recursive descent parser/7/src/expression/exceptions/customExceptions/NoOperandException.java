package expression.exceptions.customExceptions;

public class NoOperandException extends ParserException {
    public NoOperandException(int index, String exp) {
        super("operand missed", index, exp);
    }
}
