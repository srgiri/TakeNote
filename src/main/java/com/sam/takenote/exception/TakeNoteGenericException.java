package com.sam.takenote.exception;

import com.sam.takenote.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class TakeNoteGenericException extends RuntimeException {
    private String errorMessage;
    private TakeNoteErrorCodes statusCode;

    public TakeNoteGenericException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.statusCode = TakeNoteErrorCodes.TAKE_NOTE_SERVER_ERROR;
    }

    public TakeNoteGenericException(String errorMessage, TakeNoteErrorCodes statusCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String toJson() {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("errorCode", String.valueOf(statusCode.getCode()));
        jsonMap.put("errorMessage", errorMessage);
        return JsonUtil.toJson(jsonMap);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }

    public void setStatusCode(TakeNoteErrorCodes statusCode) {
        this.statusCode = statusCode;
    }
}
