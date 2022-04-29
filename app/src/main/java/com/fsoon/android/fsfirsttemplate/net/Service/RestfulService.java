package com.fsoon.android.fsfirsttemplate.net.Service;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.fsoon.android.fsfirsttemplate.BuildConfig;
import com.fsoon.android.fsfirsttemplate.FsApp;
import com.fsoon.android.fsfirsttemplate.common.util.LogUtil;
import com.fsoon.android.fsfirsttemplate.net.APIConstants;
import com.fsoon.android.fsfirsttemplate.net.FsAPI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestfulService {
    private static final int HTTP_READ_TIMEOUT = 5000;
    private static final int HTTP_CONNECT_TIMEOUT = 4000;

    private static FsAPI apiInterface;

    public synchronized static FsAPI getInstance(Context c) {
        if(apiInterface == null) {
            apiInterface = provideApiService(c);
        }
        return apiInterface;
    }

    public void onDestroy() {
        apiInterface = null;
    }

    static private FsAPI provideApiService(Context c) {
        return provideRetrofit(APIConstants.BASE_URL, provideClient(c, true)).create(FsAPI.class);
    }

    /** use for responseBodyConverter() 사용, 비정상적인 서버 데이터 */
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static public FsAPI provideApiService(boolean addParameter) {
        return provideRetrofit(APIConstants.BASE_URL, provideClient(null, addParameter)).create(FsAPI
                .class);
    }

    //private static Retrofit retrofit;
    //private static Gson gson;
    /** use for template */
    static public FsAPI provideApiService(Context c, String host, boolean addParameter) {
        return provideRetrofit(host, provideClient(c, addParameter)).create(FsAPI.class);
    }

    static private Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        /*if(gson == null) {
            gson = new GsonBuilder()   // com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) 대응
                    .setLenient()
                    .create();
        }*/
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(NullOnEmptyConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())  // post 날릴려면 필요!
                .addConverterFactory(GsonConverterFactory.create(/*gson*/))
                .build();
    }
    /*static OkHttpClient provideClient(Context c) {
        return provideClient(c, true);
    }*/
    static OkHttpClient provideClient(final Context c, final boolean addParameter) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url;

                request = request.newBuilder()
                        .addHeader("APP-PACKAGE-NAME", "com.kt.android.goodpay")
                .addHeader("SERVICE-NAME", "AP0000000001")
//                        .addHeader("X-Naver-Client-Id", APIConstants.NAVER_CLIENT_ID)
//                        .addHeader("X-Naver-Client-Secret", APIConstants.NAMER_CLIENT_SECRET)
                        .build();

                if(addParameter) {
                    /* 공통 parameters */
                    url = request.url().newBuilder()
//                            .addQueryParameter(APIConstants.APIKEY, appData.getApiKey())
//                            .addQueryParameter(APIConstants.CREDENTIAL, appData.getCredential())
                            .build();

                } else {
                    url = request.url().newBuilder().build();
                }
                request = request.newBuilder().url(url).build();
                LogUtil.w("API", "request url : "+request.url());
                return chain.proceed(request);
            }
        })
        .connectTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor);

        return enableTlsOnPreLollipop(httpClientBuilder).build();
    }

    private static OkHttpClient.Builder enableTlsOnPreLollipop(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                builder.sslSocketFactory(new TLSSocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                builder.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return builder;
    }
}