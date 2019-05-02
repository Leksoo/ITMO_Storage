package expression.exceptions.customExceptions;

public class WrongInputFormatException extends ParserException {
    public WrongInputFormatException(int index, String exp) {
        super("wrong input format, can't identify symbol", index, exp);
    }
}
