package com.fsoon.android.fsfirsttemplate.net.model;

/**
 * Description:
 *
 * retrofit errorBody 에러 처리
 * RetrofitErrorUtils
 */
public class ResponseError extends ResponseBase{

    private Object resultCode;
    private String resultMessage;
    private String resultOptional;

    public ResponseError(int returnCode, String msg, String resultOptional) {
        this.resultCode = returnCode;
        this.resultMessage = msg;
        this.resultOptional = resultOptional;
    }

    public ResponseError(int code, String msg) {
        super(code, msg);
    }

    // 코드가 string 으로 넘어올때 예외처리 edit by hyk (kkj phone 에서 발생)
    public int getResultCode() {
        if(resultCode instanceof Integer) {
            return (int)resultCode;
        }
        return RETURN_CODE_999;
    }

    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultOptional() {
        return resultOptional;
    }

    public void setResultOptional(String resultOptional) {
        this.resultOptional = resultOptional;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultOptional='" + resultOptional + '\'' +
                '}';
    }
}