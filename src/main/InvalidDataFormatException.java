package main;

public class InvalidDataFormatException extends Exception{
    public InvalidDataFormatException() {
        super();
    }

    public InvalidDataFormatException(String message) {
        super(message);
    }
}
