package com.fsoon.android.fsfirsttemplate.net;

public interface APIResultListener<T> {

    void onSuccess(APIConstants.URL requestId, T t);

    void onError(APIConstants.URL requestId, int code, String message);

}
