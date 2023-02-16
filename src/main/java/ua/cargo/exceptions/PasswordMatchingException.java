package ua.cargo.exceptions;


import static ua.cargo.exceptions.constants.Message.PASSWORD_MATCHING;

public class PasswordMatchingException extends ServiceException{
    public PasswordMatchingException() {
        super(PASSWORD_MATCHING);
    }
}