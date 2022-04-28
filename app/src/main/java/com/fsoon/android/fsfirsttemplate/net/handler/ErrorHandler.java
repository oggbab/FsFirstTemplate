package com.fsoon.android.fsfirsttemplate.net.handler;

import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.ErrorOutput;

public interface ErrorHandler {

    boolean handleError(ErrorOutput errorOutput, APIConstants.URL requestId, Throwable e);

}