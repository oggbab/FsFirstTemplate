package com.fsoon.android.fsfirsttemplate.net.model

import com.google.gson.annotations.SerializedName
import android.text.TextUtils
import kotlin.jvm.Synchronized
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.converter.scalars.ScalarsConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import com.fsoon.android.fsfirsttemplate.BuildConfig
import okhttp3.Interceptor
import kotlin.Throws
import okhttp3.HttpUrl
import android.os.Build
import okhttp3.ConnectionSpec
import okhttp3.TlsVersion
import retrofit2.Converter
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import org.json.JSONObject
import org.json.JSONException
import com.google.gson.JsonObject
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fsoon.android.fsfirsttemplate.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Intent
import android.widget.Toast
import android.widget.TextView
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.DialogInterface
import android.util.DisplayMetrics
import android.view.WindowManager
import android.util.TypedValue
import android.widget.EditText
import android.annotation.TargetApi
import androidx.core.content.ContextCompat
import android.widget.LinearLayout
import android.view.ViewConfiguration
import android.view.KeyCharacterMap
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.ComponentName
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.google.android.material.tabs.TabLayout
import android.view.ViewGroup.MarginLayoutParams
import android.app.ActivityManager.RunningAppProcessInfo
import java.io.Serializable

open class ResponseBase : Serializable {

    @SerializedName("retcode")
    private var resultCode: String = RESPONSE_OK

    @SerializedName("retmsg")
    private var retmsg: String = ""

    fun getResultMessage() {
        if (TextUtils.isEmpty(retmsg)) ""
        else retmsg.replace("\\n", "\n")
    }

    constructor(code: String?, msg: String?) {
        code?.let { resultCode = it }
        msg?.let { retmsg = it }
    }


    val isSuccess: Boolean
        get() = resultCode == RESPONSE_OK

    override fun toString(): String {
        return "ResponseBase{" +
                "retcode=" + resultCode +
                ", retmsg='" + retmsg + '\'' +
                '}'
    }

    companion object {
        //api response return code
        const val RESPONSE_OK = "OK"
        const val RESPONSE_FAIL = "FAIL"
    }
}