package com.fsoon.android.fsfirsttemplate.net.Service

import android.content.Context
import android.os.Build
import android.util.Log
import com.fsoon.android.fsfirsttemplate.BuildConfig
import com.fsoon.android.fsfirsttemplate.common.util.LogUtil
import com.fsoon.android.fsfirsttemplate.net.APIConstants
import com.fsoon.android.fsfirsttemplate.net.FsAPI
import okhttp3.ConnectionSpec
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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