package com.fsoon.android.fsfirsttemplate.net.model.search

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
import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase

class ResponseShopSearchItem : ResponseBase {
    constructor(msg: String?) : super(RESPONSE_FAIL, msg)
    constructor(code: String?, msg: String?) : super(code, msg)

    @SerializedName("title")
    var title //검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
            : String? = null

    @SerializedName("link")
    var link //검색 결과 문서의 하이퍼텍스트 link를 나타낸다.
            : String? = null

    @SerializedName("image")
    var image //썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타난다.
            : String? = null

    @SerializedName("lprice")
    var lprice //최저가 정보이다. 최저가 정보가 없는 경우 0으로 표시되며, 가격비교 데이터가 없는 경우 이 필드는 가격을 나타낸다.
            : String? = null

    @SerializedName("hprice")
    var hprice //최고가 정보이다. 최고가 정보가 없거나 가격비교 데이터가 없는 경우 0으로 표시된다.
            : String? = null

    @SerializedName("mallName")
    var mallName //상품을 판매하는 쇼핑몰의 상호이다. 정보가 없을 경우 네이버로 표기된다.
            : String? = null

    @SerializedName("productId")
    var productId //해당 상품에 대한 ID 이다.
            : String? = null

    @SerializedName("productType")
    var productType //상품군 정보를 일반상품, 중고상품, 단종상품, 판매예정상품으로 구분한다.
            : String? = null

    @SerializedName("maker")
    var maker //해당 상품의 제조사 명이다.
            : String? = null

    @SerializedName("brand")
    var brand //해당 상품의 브랜드 명이다.
            : String? = null

    @SerializedName("category1")
    var category1 //해당 상품의 카테고리, 대분류이다.
            : String? = null

    @SerializedName("category2")
    var category2 //해당 상품의 카테고리, 중분류이다.
            : String? = null

    @SerializedName("category3")
    var category3 //해당 상품의 카테고리, 소분류이다.
            : String? = null

    @SerializedName("category4")
    var category4 //해당 상품의 카테고리, 세분류이다.
            : String? = null
}