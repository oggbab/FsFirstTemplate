package com.fsoon.android.fsfirsttemplate.net;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

abstract public class ResponseHeader {
    String returnCode;
    String message;
    JsonElement result;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String code) {
        this.returnCode = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }

    public boolean isSuccess() {
        boolean retVal = false;
        /*if (returnCode != null && message != null) {
            retVal = APIConstants.RETURN_CODE_200.equals(returnCode) &&
                    APIConstants.RETURN_MESSAGE_SUCCESS.equals(message);
        }*/
        return retVal;
    }

    abstract public void parseResult();
}
