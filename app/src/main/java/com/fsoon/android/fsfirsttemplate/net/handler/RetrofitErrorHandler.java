package com.fsoon.android.fsfirsttemplate.net.handler;

import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.ErrorOutput;
import com.fsoon.android.fsfirsttemplate.net.exception.RetrofitException;

import java.io.IOException;

/**
 * Look this : http://bytes.babbel.com/en/articles/2016-03-16-retrofit2-rxjava-error-handling.html
 */
public class RetrofitErrorHandler extends DefaultErrorHandler {

    private static final String NETWORK_ERROR = "network error";
    private static final String UNEXPECTED_ERROR = "unexpected error";
    private static final String UNHANDLED_ERROR = "unhandled error";

    @Override
    public boolean handleError(ErrorOutput errorOutput, APIConstants.URL requestId,
                               Throwable throwable) {
        if (!(throwable instanceof RetrofitException)) {
            return false;
        }

        String errorMessage;
        RetrofitException retrofitException = (RetrofitException) throwable;
        Throwable exceptionToReturn;

        switch (retrofitException.getKind()) {
            case HTTP:
                try {
                    APIError apiError = retrofitException.getErrorBodyAs(APIError.class);
                    errorMessage = String.format("Server error : %s", apiError.getError());
                    exceptionToReturn = new RuntimeException(apiError.toString(), throwable);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case NETWORK:
                errorMessage = NETWORK_ERROR;
                exceptionToReturn = new RuntimeException(errorMessage, throwable);
                break;
            case UNEXPECTED:
                errorMessage = UNEXPECTED_ERROR;
                exceptionToReturn = new RuntimeException(errorMessage, throwable);
                break;
            case FAILED:
                errorMessage = retrofitException.getMessage();
                exceptionToReturn = new RuntimeException(retrofitException.getMessage(), throwable);
                break;
            default:
                errorMessage = UNHANDLED_ERROR;
                exceptionToReturn = new RuntimeException(errorMessage, throwable);
                break;
        }

        outputErrors(errorOutput, requestId, errorMessage, exceptionToReturn);

        return true;
    }

    class APIError {

        private long timestamp;
        private int status;
        private String error;
        private String exception;
        private String message;
        private String path;

        public long getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getException() {
            return exception;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public String toString() {
            return error;
        }

    }

}