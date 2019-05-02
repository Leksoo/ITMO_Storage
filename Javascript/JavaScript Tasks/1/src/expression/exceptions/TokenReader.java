package expression.exceptions;

import expression.exceptions.customExceptions.*;

import java.util.EnumSet;
import java.util.Set;


public class TokenReader {
    private int index;
    private int numberToken;
    private Values current;
    private char currentVariable;
    private String expression;
    private int balance;

    private final Set<Values> operations = EnumSet.of(Values.DIV, Values.PLUS, Values.SUB, Values.MUL, Values.MINUS);
    private final Set<Values> operands = EnumSet.of(Values.VARIABLE, Values.NUMBER);


    TokenReader(String expression) {
        this.expression = expression;
        index = 0;
        numberToken = 0;

        current = Values.START;
        currentVariable = 0;
        balance = 0;
    }

    public int getIndex() {
        return index;
    }

    public int getNumberToken() {
        return numberToken;
    }

    public Values getCurrent() {
        return current;
    }


    public char getCurrentVariable() {
        return currentVariable;
    }

    public String getExpression() {
        return expression;
    }


    private int readNumber(char sign) throws ParserException {
        StringBuilder number = new StringBuilder();
        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            number.append(expression.charAt(index));
            index++;
        }
        index--;
        int ans;
        try {
            ans = Integer.parseInt(sign + number.toString());
        } catch (NumberFormatException e) {
            throw new WrongNumberFormatException(index, expression);
        }
        return ans;
    }

    private void whiteSpaceClearing() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    private void operandCheck() throws NoOperandException {
        if (operations.contains(current) || current == Values.LP || current == Values.START) {
            throw new NoOperandException(index, expression);
        }
    }

    private void operatorCheck() throws NoOperatorException {
        if (operands.contains(current) || current == Values.RP) {
            throw new NoOperatorException(index, expression);
        }
    }


    public void getToken() throws ParserException {
        whiteSpaceClearing();
        if (index >= expression.length()) {
            operandCheck();
            current = Values.END;
            return;
        }
        switch (expression.charAt(index)) {
            case '+': {
                operandCheck();
                current = Values.PLUS;
                break;
            }
            case '*': {
                operandCheck();
                current = Values.MUL;
                break;
            }
            case '/': {
                operandCheck();
                current = Values.DIV;
                break;
            }
            case '-': {
                // binary op SUB
                if (current == Values.NUMBER || current == Values.RP || current == Values.VARIABLE) {
                    current = Values.SUB;
                }
                // unary minus
                else {
                    // - nothing
                    if (index >= expression.length() - 1) {
                        throw new NoOperandException(index, expression);
                    }
                    // - number
                    if (Character.isDigit(expression.charAt(index + 1))) {
                        index++;
                        numberToken = readNumber('-');
                        current = Values.NUMBER;
                    }
                    // just -
                    else {
                        current = Values.MINUS;
                    }
                }
                break;


            }
            case '(': {
                operatorCheck();
                current = Values.LP;
                balance++;
                break;
            }
            case ')': {
                balance--;
                if (balance < 0) {
                    throw new BracketsBalanceException(index, expression);
                }
                if (operations.contains(current) || current == Values.LP) {
                    throw new NoOperandException(index, expression);
                }
                current = Values.RP;
                break;

            }
            case 'x':
            case 'y':
            case 'z': {
                operatorCheck();
                current = Values.VARIABLE;
                currentVariable = expression.charAt(index);
                break;
            }
            case 'l':
            case 'p': {
                String op = expression.substring(index, index + 5);
                switch (op) {
                    case "pow10":
                        current = Values.POW10;
                        break;
                    case "log10":
                        current = Values.LOG10;
                        break;
                    default:
                        throw new WrongInputFormatException(index, expression);
                }
                index += 4;
                break;
            }
            default: {
                // number
                if (Character.isDigit(expression.charAt(index))) {
                    operatorCheck();
                    numberToken = readNumber('+');
                    current = Values.NUMBER;
                } else {
                    throw new WrongInputFormatException(index, expression);
                }
            }

        }
        index++;

    }
}
