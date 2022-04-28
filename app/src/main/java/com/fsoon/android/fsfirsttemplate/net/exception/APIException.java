package com.fsoon.android.fsfirsttemplate.net.exception;

import androidx.annotation.NonNull;

public class APIException extends RuntimeException {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int UNKNOWN = 0;

    private final String message;
    private final int code;

    private APIException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static APIException error(@NonNull String returnCode, @NonNull String message) {
        int code = Integer.parseInt(returnCode);
        switch (code) {
            case UNAUTHORIZED:
                return new APIException(message, UNAUTHORIZED);
            case FORBIDDEN:
                return new APIException(message, FORBIDDEN);
            case NOT_FOUND:
                return new APIException(message, NOT_FOUND);
            case METHOD_NOT_ALLOWED:
                return new APIException(message, METHOD_NOT_ALLOWED);
            default:
                return new APIException(message, UNKNOWN);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}