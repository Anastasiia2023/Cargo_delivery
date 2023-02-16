package ua.cargo.exceptions;


import static ua.cargo.exceptions.constants.Message.DUPLICATE_EMAIL;

public class DuplicateEmailException extends ServiceException {
    public DuplicateEmailException() {
        super(DUPLICATE_EMAIL);
    }
}