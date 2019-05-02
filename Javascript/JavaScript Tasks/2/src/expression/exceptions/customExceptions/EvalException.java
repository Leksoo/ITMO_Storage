package expression.exceptions.customExceptions;

public class EvalException extends Exception {
    EvalException(String message) {
        super(message);
        // System.out.println("Evaluation problem: "+ message);

    }
}
