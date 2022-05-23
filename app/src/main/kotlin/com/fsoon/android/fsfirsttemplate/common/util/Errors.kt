package com.fsoon.android.fsfirsttemplate.common.util

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
import android.content.Context
import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase

class Errors {

    companion object {

        fun show(context: Context, res: ResponseBase, isBack: Boolean) {
            if (context == null || (context as AppCompatActivity).isFinishing) return

            var message: String = if (!TextUtils.isEmpty(res.resultMessage)) res.resultMessage else context.getResources()
                    .getString(R.string.text_error_api)

            Utils.DialogShowOneBt(
                context,
                context.getResources().getString(R.string.app_name),
                message,
                context.getResources().getString(R.string.str_ok)
            ) { dialog, which ->
                if (which == Utils.MSG_OK) {
                    dialog.dismiss()
                    if (isBack) context.onBackPressed()
                }
            }
        }

        fun showExitPopup(context: Context, res: ResponseBase) {
            var message =
                if (!TextUtils.isEmpty(res.resultMessage)) res.resultMessage else context.resources.getString(
                    R.string.dialog_network_error
                )

            Utils.DialogShowOneBt(
                context as AppCompatActivity,
                context.getResources().getString(R.string.app_name),
                message,
                context.getResources().getString(R.string.str_ok)
            ) { dialog, which ->
                if (which == Utils.MSG_OK) {
                    dialog.dismiss()
                    Utils.quitApp(context)
                }
            }
        }
    }
}