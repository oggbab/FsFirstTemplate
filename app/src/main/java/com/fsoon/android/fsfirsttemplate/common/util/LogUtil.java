package com.fsoon.android.fsfirsttemplate.common.util;

import android.util.Log;
import com.fsoon.android.fsfirsttemplate.BuildConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtil {

    private static final String TAG_EXCEPTION = "exception";
    private static final boolean fileOut = false;
    public static final boolean debug = true;

    public static void v(String TAG, String msg) {
        if(debug && BuildConfig.DEBUG){
            Log.v(TAG,msg);
        }
        writeFile(TAG,msg);
    }

    public static void d(String TAG, String msg) {
        if(debug && BuildConfig.DEBUG){
            //로그 잘림 방지 코드 for GvList
            if (msg.length() > 3000) {
                Log.d(TAG, msg.substring(0, 3000));
                d(TAG, msg.substring(3000));
            } else {
                Log.d(TAG, msg);
            }

//            Log.d(TAG, msg);
        }
        writeFile(TAG,msg);
    }


    public static void w(String TAG, String msg) {
        if(debug && BuildConfig.DEBUG){
            Log.w(TAG,msg);
        }
        writeFile(TAG,msg);
    }
    public static void e(String TAG, String msg) {
        if(debug && BuildConfig.DEBUG){
            Log.e(TAG,msg);
        }
        writeFile(TAG,msg);
    }

    public static void i(String TAG, String msg) {
        if(debug && BuildConfig.DEBUG){
            Log.i(TAG,msg);
        }
        writeFile(TAG,msg);
    }

    private static void writeFile(String TAG, String msg) {
        if ( fileOut ) {
            appendLog(TAG+"|"+msg);
//            LogWrapper.getInstance().v(TAG,msg);
        }
    }

    public static void appendLog(String text)
    {
        File logFile = new File("sdcard/log.txt");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // 적절한 예외처리를 해주면됩니다.
                LogUtil.logException(e.getMessage());
            }
        }

        BufferedWriter buf = null;

        try
        {
            //퍼포먼스를 위해 BufferedWriter를 썼고 FileWriter의 true는 파일을 이어쓸 수 있게 하기 위해서 해줬습니다.
            buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // 적절한 예외처리를 해주면됩니다.
            LogUtil.logException(e.getMessage());
        } finally {
            if(buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    LogUtil.logException(e.getMessage());
                }
            }
        }
    }

    public static void logException(String msg) {
        e(TAG_EXCEPTION, msg);
    }

}
