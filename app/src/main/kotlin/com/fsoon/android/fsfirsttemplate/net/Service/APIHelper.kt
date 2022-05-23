package com.fsoon.android.fsfirsttemplate.net.Service

import com.google.gson.annotations.SerializedName
import android.text.TextUtils
import kotlin.jvm.Synchronized
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
import android.content.Context
import retrofit2.*

object APIHelper {
    const val DEFAULT_RETRIES = 3

    fun <T> enqueueWithRetry(call: Call<T>, retryCount: Int, callback: Callback<T>) {
        call.enqueue(object : RetryableCallback<T>(call, retryCount) {
            override fun onFinalResponse(call: Call<T>?, response: Response<T>?) {
                callback.onResponse(call, response)
            }

            override fun onFinalFailure(call: Call<T>?, t: Throwable?) {
                callback.onFailure(call, t)
            }
        })
    }

    fun <T> enqueueWithRetry(context: Context?, call: Call<T>, callback: Callback<T>) {
        enqueueWithRetry(call, DEFAULT_RETRIES, callback)
    }

    @kotlin.jvm.JvmStatic
    fun isCallSuccess(response: Response<*>): Boolean {
        val code = response.code()
        return code in 200..399
    }
}