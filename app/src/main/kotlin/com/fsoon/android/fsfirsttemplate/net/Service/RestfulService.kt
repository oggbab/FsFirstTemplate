package com.fsoon.android.fsfirsttemplate.net.Service

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
import android.util.Log
import com.fsoon.android.fsfirsttemplate.common.util.LogUtil
import com.fsoon.android.fsfirsttemplate.net.APIConstants
import com.fsoon.android.fsfirsttemplate.net.ApiManager
import com.fsoon.android.fsfirsttemplate.net.FsAPI
import java.lang.Exception
import java.util.ArrayList
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

class RestfulService {
    fun onDestroy() {
        apiInterface = null
    }

    companion object {
        private const val HTTP_READ_TIMEOUT = 5000
        private const val HTTP_CONNECT_TIMEOUT = 4000
        private var apiInterface: FsAPI? = null

        @Synchronized
        fun getInstance(c: Context): FsAPI {
            return apiInterface ?: provideApiService(c).also {
                apiInterface = it
            }
        }

        private fun provideApiService(c: Context): FsAPI {
            return provideRetrofit(APIConstants.BASE_URL, provideClient(c, true)).create(
                FsAPI::class.java
            )
        }

        /** use for responseBodyConverter() 사용, 비정상적인 서버 데이터  */
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        fun provideApiService(addParameter: Boolean): FsAPI {
            return provideRetrofit(APIConstants.BASE_URL, provideClient(null, addParameter)).create(
                FsAPI::class.java
            )
        }
        //private static Retrofit retrofit;
        //private static Gson gson;
        /** use for template  */
        fun provideApiService(c: Context?, host: String?, addParameter: Boolean): FsAPI {
            return provideRetrofit(host, provideClient(c, addParameter)).create(
                FsAPI::class.java
            )
        }

        private fun provideRetrofit(baseURL: String?, client: OkHttpClient): Retrofit {
            /*if(gson == null) {
            gson = new GsonBuilder()   // com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) 대응
                    .setLenient()
                    .create();
        }*/
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(NullOnEmptyConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()) // post 날릴려면 필요!
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        /*static OkHttpClient provideClient(Context c) {
        return provideClient(c, true);
    }*/

        fun provideClient(c: Context?, addParameter: Boolean): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor { chain ->
                val url: HttpUrl
                var request = chain.request()
                request = request.newBuilder() //착한페이
                    .addHeader("APP-PACKAGE-NAME", "com.kt.android.goodpay")
                    .addHeader("SERVICE-NAME", "AP0000000001") //네이버
                    //                        .addHeader("X-Naver-Client-Id", APIConstants.NAVER_CLIENT_ID)
                    //                        .addHeader("X-Naver-Client-Secret", APIConstants.NAMER_CLIENT_SECRET)
                    .build()
                url = if (addParameter) {
                    /* 공통 parameters */
                    request.url()
                        .newBuilder() //                            .addQueryParameter(APIConstants.APIKEY, appData.getApiKey())
                        //                            .addQueryParameter(APIConstants.CREDENTIAL, appData.getCredential())
                        .build()
                } else {
                    request.url().newBuilder().build()
                }
                request = request.newBuilder().url(url).build()
                LogUtil.w("API", "request url : " + request.url())
                chain.proceed(request)
            }
                .connectTimeout(HTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(HTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(interceptor)
            return enableTlsOnPreLollipop(httpClientBuilder).build()
        }

        private fun enableTlsOnPreLollipop(builder: OkHttpClient.Builder): OkHttpClient.Builder {
            if (Build.VERSION.SDK_INT in 16..21) {
                try {
                    val sc = SSLContext.getInstance("TLSv1.2")
                    sc.init(null, null, null)
                    builder.sslSocketFactory(TLSSocketFactory(sc.socketFactory))

                    val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build()

                    val specs: MutableList<ConnectionSpec> = ArrayList()
                    specs.apply {
                        this.add(cs)
                        this.add(ConnectionSpec.COMPATIBLE_TLS)
                        this.add(ConnectionSpec.CLEARTEXT)
                    }
                    builder.connectionSpecs(specs)
                } catch (exc: Exception) {
                    Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc)
                }
            }
            return builder
        }
    }
}