package ru.ifmo.rain.feponov.walk;

public class WalkException extends Exception {
    public WalkException(String description, String message) {
        super(description + " : " + message);
    }
}
