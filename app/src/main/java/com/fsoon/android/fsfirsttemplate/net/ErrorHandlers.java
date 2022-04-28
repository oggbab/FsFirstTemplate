package com.fsoon.android.fsfirsttemplate.net;

import com.fsoon.android.fsfirsttemplate.net.handler.DefaultErrorHandler;
import com.fsoon.android.fsfirsttemplate.net.handler.ErrorHandler;
import com.fsoon.android.fsfirsttemplate.net.handler.RetrofitErrorHandler;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandlers {

    private final List<ErrorHandler> errorHandlersList;

    public ErrorHandlers() {
        this.errorHandlersList = new ArrayList<ErrorHandler>() {
            {
                add(new RetrofitErrorHandler());
                add(new DefaultErrorHandler());
            }
        };
    }

    public List<ErrorHandler> getErrorHandlers() {
        return errorHandlersList;
    }
}