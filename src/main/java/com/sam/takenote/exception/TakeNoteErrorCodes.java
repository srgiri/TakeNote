package com.sam.takenote.exception;

public enum TakeNoteErrorCodes {
    TAKE_NOTE_SERVER_ERROR(500), CLIENT_TOKEN_ERROR(401), DATA_ALREADY_EXISTS(402),
    INVALID_CREDENTIALS(403), DATA_NOT_FOUND(404);

    final int code;

    TakeNoteErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
