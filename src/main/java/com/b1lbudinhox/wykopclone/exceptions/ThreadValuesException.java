package com.b1lbudinhox.wykopclone.exceptions;

public class ThreadValuesException extends RuntimeException {
    public ThreadValuesException(String exmessage) {
        super(exmessage);
    }
    public ThreadValuesException(String exmessage, Exception exception){
        super(exmessage, exception);
    }
}
