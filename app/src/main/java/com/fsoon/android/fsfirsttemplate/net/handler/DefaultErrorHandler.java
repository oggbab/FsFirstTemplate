package com.fsoon.android.fsfirsttemplate.net.handler;

import com.fsoon.android.fsfirsttemplate.common.util.LogUtil;
import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.ErrorOutput;

import retrofit2.HttpException;

public class DefaultErrorHandler implements ErrorHandler {

    private static final String TAG = DefaultErrorHandler.class.getSimpleName();

    @Override
    public boolean handleError(ErrorOutput errorOutput, APIConstants.URL requestId,
                               Throwable throwable) {
        String errorMessage = throwable != null ? throwable.getMessage() : "Unknown error";
        outputErrors(errorOutput, requestId, errorMessage, throwable);
        return true;
    }

    protected void outputErrors(ErrorOutput errorOutput, APIConstants.URL requestId,
                                String errorBody, Throwable throwable) {
        String errorMessage = errorBody != null ? errorBody : (throwable != null ?
                throwable.getMessage() : "Unknown error");
        if (errorOutput != null) {
            try {
                int code = -1;
                if (throwable instanceof HttpException) {
                    code = ((HttpException) throwable).code();
                }
                errorOutput.showError(requestId, code, errorMessage);
            } catch (Exception ee) {
                LogUtil.e(TAG, ee.getMessage());
            }
        }

        String logMessage = String.format("%s : %s", requestId.name(), errorMessage);
        if (throwable != null) {
            LogUtil.e(TAG, logMessage);
            //Crashlytics.logException(e);
        } else {
            LogUtil.e(TAG, logMessage);
        }
    }

}
