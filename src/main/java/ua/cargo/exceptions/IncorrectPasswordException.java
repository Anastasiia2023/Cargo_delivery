package ua.cargo.exceptions;


import static ua.cargo.exceptions.constants.Message.WRONG_PASSWORD;

public class IncorrectPasswordException extends ServiceException{
    public IncorrectPasswordException() {
        super(WRONG_PASSWORD);
    }
}