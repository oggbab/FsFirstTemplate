package com.fsoon.android.fsfirsttemplate.net;

public interface ErrorOutput {

    void showError(APIConstants.URL requestId, int code, String message);

}