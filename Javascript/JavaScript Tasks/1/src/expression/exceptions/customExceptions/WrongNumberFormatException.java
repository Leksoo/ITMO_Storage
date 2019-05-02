package expression.exceptions.customExceptions;

public class WrongNumberFormatException extends ParserException {

    public WrongNumberFormatException(int index, String exp) {
        super("wrong number format", index, exp);
    }
}
