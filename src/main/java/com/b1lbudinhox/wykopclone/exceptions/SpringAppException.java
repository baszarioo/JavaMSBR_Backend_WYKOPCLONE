package com.b1lbudinhox.wykopclone.exceptions;

public class SpringAppException extends RuntimeException {
    public SpringAppException(String excMessage, Exception exception) {
        super(excMessage, exception);
    }
    public SpringAppException(String excMessage) {
        super(excMessage);
    }
}
