package ua.cargo.exceptions;


import ua.cargo.exceptions.constants.Message;

public class DuplicateTitleException extends ServiceException {
    public DuplicateTitleException() {
        super(Message.DUPLICATE_TITLE);
    }
}