package expression.exceptions.customExceptions;


public class ParserException extends Exception {
    public ParserException(String message, int index, String exp) {
        super(message + ": happened in " + (index + 1) + "th element of expression: " + exp);
        //  System.out.println("Parsing problem: "+ message);
    }
}
