package com.fsoon.android.fsfirsttemplate.view.tamplate

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
import android.content.Intent
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.DialogInterface
import android.util.DisplayMetrics
import android.util.TypedValue
import android.annotation.TargetApi
import androidx.core.content.ContextCompat
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.ComponentName
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.google.android.material.tabs.TabLayout
import android.view.ViewGroup.MarginLayoutParams
import android.app.ActivityManager.RunningAppProcessInfo
import android.view.*
import android.widget.*
import com.fsoon.android.fsfirsttemplate.view.base.BaseActivity

class ScrollingHeaderActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mLeftButton: ImageView
    private lateinit var mRightButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_header)
    }

    fun init(title: String?) {
        initButton()
        setTitle(title)
    }

    fun init(title: String?, lVisible: Boolean, rVisible: Boolean) {
        initButton()
        setTitle(title)
        setLeftButtonVisible(lVisible)
        setRightButtonVisible(rVisible)
    }

    fun init(
        title: String?,
        lVisible: Boolean,
        rVisible: Boolean,
        lEnable: Boolean,
        rEnable: Boolean
    ) {
        initButton()
        setTitle(title)
        setLeftButtonVisible(lVisible)
        setRightButtonVisible(rVisible)
        setLeftButtonEnable(lEnable)
        setRightButtonEnable(rEnable)
    }

    fun setTitle(title: String?) {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title
    }

    private fun initButton() {
        mLeftButton = findViewById(R.id.rightButton)
        mRightButton = findViewById(R.id.leftButton)
        mLeftButton.setOnClickListener(this)
        mRightButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.leftButton -> {}
            R.id.rightButton -> {}
        }
    }

    fun setLeftButtonVisible(isVisible: Boolean) {
        mLeftButton!!.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        mLeftButton!!.isClickable = isVisible
    }

    fun setRightButtonVisible(isVisible: Boolean) {
        mRightButton!!.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        mRightButton!!.isClickable = isVisible
    }

    fun setLeftButtonEnable(isEnable: Boolean) {
        mLeftButton!!.isEnabled = isEnable
    }

    fun setRightButtonEnable(isEnable: Boolean) {
        mRightButton!!.isEnabled = isEnable
    }
}