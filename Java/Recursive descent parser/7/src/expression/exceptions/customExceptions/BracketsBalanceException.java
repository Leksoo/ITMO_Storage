package expression.exceptions.customExceptions;

public class BracketsBalanceException extends ParserException {
    public BracketsBalanceException(int index, String exp) {
        super("wrong brackets number or order", index, exp);
    }
}
