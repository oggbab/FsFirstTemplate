package com.fsoon.android.fsfirsttemplate.common.util;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;

import com.fsoon.android.fsfirsttemplate.R;
import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase;


public class Errors {
    public static void show(final Context context, ResponseBase res, final boolean isBack){
        if(context == null || ((AppCompatActivity)context).isFinishing())
            return;

        String message = null;

        if(!TextUtils.isEmpty(res.getResultMessage()))
            message = res.getResultMessage();
        else
            message = context.getResources().getString(R.string.text_error_api);

        Utils.DialogShowOneBt(((AppCompatActivity)context),
                context.getResources().getString(R.string.app_name),
                message,
                context.getResources().getString(R.string.str_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == Utils.MSG_OK) {
                            dialog.dismiss();
                            if(isBack)
                                ((AppCompatActivity)context).onBackPressed();
                        }
                    }
                }
        );
    }

    public static void showExitPopup(final Context context, ResponseBase res){
        String message = null;

        if(!TextUtils.isEmpty(res.getResultMessage()))
            message = res.getResultMessage();
        else
            message = context.getResources().getString(R.string.dialog_network_error);

        Utils.DialogShowOneBt(((AppCompatActivity)context),
                context.getResources().getString(R.string.app_name),
                message,
                context.getResources().getString(R.string.str_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == Utils.MSG_OK) {
                            dialog.dismiss();
                            Utils.quitApp((AppCompatActivity)context);
                        }
                    }
                }
        );
    }
}
