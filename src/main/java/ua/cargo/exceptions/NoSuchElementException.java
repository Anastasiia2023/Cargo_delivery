package ua.cargo.exceptions;


import ua.cargo.exceptions.constants.Message;

public class NoSuchElementException extends ServiceException {
    public NoSuchElementException() {
        super(Message.NO_ELEMENT);
    }
    public NoSuchElementException(String message) {
        super(message);
    }
}