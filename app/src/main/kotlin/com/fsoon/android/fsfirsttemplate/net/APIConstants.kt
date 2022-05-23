package com.fsoon.android.fsfirsttemplate.net

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

object APIConstants {
    const val NAVER_CLIENT_ID = "8KkD6sXBIwOKaxtwXr9K"
    const val NAMER_CLIENT_SECRET = "4sUuJdfvdT"

    //    public static final String BASE_URL = "https://openapi.naver.com/v1/";
    const val BASE_URL = "http://210.179.57.140/"

    // 검색
    const val URL_SHOP_SEARCH = "search/shop.json" // 검색결과 목록
    const val URL_SEARCH_KEYWORD = "search/{type}/keywords" // 자동완성 검색 키워드, 인기, 추천 검색어
    const val URL_APP_VERSION_INFO = "localpay/mobile/main/getVersionInfo"

    enum class URL {
        BASE {
            override fun toString(): String {
                return BASE_URL
            }
        },
        SHOP_SEARCH {
            override fun toString(): String {
                return URL_SHOP_SEARCH
            }
        },
        APP_VERSION_INFO {
            override fun toString(): String {
                return URL_APP_VERSION_INFO
            }
        }
    }
}