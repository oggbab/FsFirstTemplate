package com.fsoon.android.fsfirsttemplate.view.test

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
import com.fsoon.android.fsfirsttemplate.common.util.Errors
import com.fsoon.android.fsfirsttemplate.net.APIConstants
import com.fsoon.android.fsfirsttemplate.net.ApiManager
import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity
import java.util.*

class TestNetworkActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_network)
        //     requestNaverApi();
        requestGoodpayVersion()
    }

    private fun requestNaverApi() {
        ApiManager.getInstance(this)
            .requestSearchShop(
                "모자",
                1,
                15,
                "sim",
                object : ApiManager.OnNetworkListener<ResponseShopSearch?> {
                    override fun OnNetworkResult(
                        requestId: APIConstants.URL,
                        res: ResponseShopSearch
                    ) {
                        if (res != null && res.isSuccess) {
                            val itemList = res.items
                            if (itemList!!.size > 0) {
                            }
                        } else {
                            Errors.show(this@TestNetworkActivity, res, false)
                        }
                    }

                    override fun OnNetworkResult(
                        requestId: APIConstants.URL?,
                        res: ResponseShopSearch?
                    ) {
                        TODO("Not yet implemented")
                    }
                })
    }

    private fun requestGoodpayVersion() {
        ApiManager.getInstance(this)
            .requestAppVersion(
                "AND", "01.00.01", "com.kt.android.goodpay",
                object : ApiManager.OnNetworkListener<ResponseAppVersion> {
                    override fun OnNetworkResult(
                        requestId: APIConstants.URL,
                        res: Any
                    ) {
                        if (res != null && res.isSuccess()) {
                            Toast.makeText(
                                this@TestNetworkActivity,
                                "version : " + res.data?.appVersion,
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Errors.show(this@TestNetworkActivity, res, true)
                        }
                    }
                })
    }
}