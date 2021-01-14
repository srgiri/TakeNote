package com.sam.takenote.exception;

public class TakeNoteGenericException extends RuntimeException {
    private String errorMessage;

    public TakeNoteGenericException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
