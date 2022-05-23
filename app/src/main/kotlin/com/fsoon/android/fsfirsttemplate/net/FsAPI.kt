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
import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch
import retrofit2.Call
import retrofit2.http.*

interface FsAPI {
    /**
     * 네이버 쇼핑 검색
     */
    @GET(APIConstants.URL_SHOP_SEARCH)
    fun requestShopSearch(
        @Query("query") query: String?,
        @Query("start") limit: Int,
        @Query("display") display: Int,
        @Query("sort") sort: String?
    ): Call<ResponseShopSearch?>?

    /**
     * 착한페이 AppVersion 조회
     * @param body
     * @return
     */
    @POST(APIConstants.URL_APP_VERSION_INFO)
    @Headers("Content-Type:application/json")
    fun requestAppVersion(
        @Body body: String?
    ): Call<ResponseAppVersion?>? //    @GET(APIConstants.URL_SEARCH_KEYWORD)
    //    Call<ArrayList<ResponseSearchInstance>> requestSearchKeywords(
    //            @Path(APIConstants.TYPE) String type,
    //            @Query(APIConstants.KEYWORD) String keyword
    //    );
    //
}