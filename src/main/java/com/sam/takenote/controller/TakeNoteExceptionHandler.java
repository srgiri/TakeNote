package com.sam.takenote.controller;

import com.sam.takenote.exception.TakeNoteGenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TakeNoteExceptionHandler {
    @ExceptionHandler(TakeNoteGenericException.class)
    public ResponseEntity<Object> handleException(TakeNoteGenericException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.toJson());
    }
}
