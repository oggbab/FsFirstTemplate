package com.fsoon.android.fsfirsttemplate.net.model;

/**
 * Description:
 *
 * retrofit errorBody 에러 처리
 * RetrofitErrorUtils
 */
public class ResponseError extends ResponseBase {

    private String resultCode;
    private String resultMessage;

    public ResponseError(String code, String msg) {
        super(code, msg);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                '}';
    }
}