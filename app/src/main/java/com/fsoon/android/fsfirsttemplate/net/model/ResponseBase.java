package com.fsoon.android.fsfirsttemplate.net.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseBase implements Serializable {
    //api response return code
    public static final String RESPONSE_OK = "OK";
    public static final String RESPONSE_FAIL = "FAIL";

    @SerializedName("retcode")
    private String retcode = RESPONSE_OK;

    @SerializedName("retmsg")
    private String retmsg = "";

    public ResponseBase(String code, String msg) {
        retcode = code;
        retmsg = msg;
    }

    public ResponseBase() { }

    public String getResultCode() {
        return retcode;
    }

    public void setResultCode(String code) {
        retcode = code;
    }

    public String getResultMessage() {
        if (TextUtils.isEmpty(retmsg))
            return "";

        return retmsg.replace("\\n", "\n");
    }

    public void setResultMessage(String msg) {
        retmsg = msg;
    }

    public boolean isSuccess() {
        return retcode.equals(RESPONSE_OK);
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "retcode=" + retcode +
                ", retmsg='" + retmsg + '\'' +
                '}';
    }
}
