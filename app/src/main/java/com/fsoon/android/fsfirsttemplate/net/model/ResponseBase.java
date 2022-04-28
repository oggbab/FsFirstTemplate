package com.fsoon.android.fsfirsttemplate.net.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseBase implements Serializable {

    @SerializedName("resultcode")
    private int resultCode = RETURN_CODE_200;

    @SerializedName("resultmessage")
    private String resultMessage = "";

    @SerializedName("resultoptional")
    private String resultOptional;

    public ResponseBase(int code, String msg) {
        resultCode = code;
        resultMessage = msg;
    }

    public ResponseBase(int code, String msg, String optional) {
        resultCode = code;
        resultMessage = msg;
        resultOptional = optional;
    }

    public ResponseBase() {
    }


    public int getResultCode() {
        if (resultCode == 0) // todo 임시로 일단!
            resultCode = RETURN_CODE_200;

        return resultCode;
    }

    public void setResultCode(int code) {
        resultCode = code;
    }

    public String getResultMessage() {
        //return resultMessage;
        if (resultMessage == null)
            return "";
        return resultMessage.replace("\\n", "\n");
    }

    public void setResultMessage(String msg) {
        resultMessage = msg;
    }

    public String getResultOptional() {
        return resultOptional;
    }

    public void setResultOptional(String optional) {
        resultOptional = optional;
    }

    //api response return code
    public static final int RETURN_CODE_200 = 200;

    public static final int RETURN_CODE_210 = 210;
    public static final int RETURN_CODE_301 = 301;
    public static final int RETURN_CODE_302 = 302;

    public static final int RETURN_CODE_550 = 550; //메시지 표시 수준 에러 (에러 메시지를 노출하고 더 이상의 작업은 없음)
    public static final int RETURN_CODE_551 = 551; //에러메시지를 노출하지 않고 추가 작업 없음     <<<< 20180307 변경됨 //추가 동작 필요 수준 에러 (에러 메시지를 노출하고 추가 작업이 있음)
    public static final int RETURN_CODE_999 = 999; // 임의 값

    public boolean isSuccess() {
        if (resultCode == 0) // todo 임시로 일단!
            return true;
        return RETURN_CODE_200 == resultCode/*RETURN_CODE_200.equals(resultCode)*/ /*&&
                APIConstants.RETURN_MESSAGE_SUCCESS.equals(message)*/;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultOptional='" + resultOptional + '\'' +
                '}';
    }
}
