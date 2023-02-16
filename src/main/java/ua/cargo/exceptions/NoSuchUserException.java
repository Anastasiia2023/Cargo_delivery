package ua.cargo.exceptions;


import static ua.cargo.exceptions.constants.Message.NO_USER;

public class NoSuchUserException extends ServiceException {
    public NoSuchUserException() {
        super(NO_USER);
    }
}