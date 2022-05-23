package com.fsoon.android.fsfirsttemplate.net.Service

import android.graphics.drawable.BitmapDrawable
import com.google.android.material.tabs.TabLayout
import android.view.ViewGroup.MarginLayoutParams
import android.app.ActivityManager.RunningAppProcessInfo
import com.fsoon.android.fsfirsttemplate.common.util.LogUtil
import retrofit2.*

abstract class RetryableCallback<T>(private val call: Call<T>, totalRetries: Int) : Callback<T> {
    private var totalRetries = 3
    private var retryCount = 0

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!APIHelper.isCallSuccess(response)) if (retryCount++ < totalRetries) {
            LogUtil.v(TAG, "Retrying API Call -  ($retryCount / $totalRetries)")
            retry()
        } else onFinalResponse(call, response) else onFinalResponse(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        LogUtil.e(TAG, t?.message)
        if (retryCount++ < totalRetries) {
            LogUtil.v(TAG, "Retrying API Call -  ($retryCount / $totalRetries)")
            retry()
        } else onFinalFailure(call, t)
    }

    private fun retry() {
        call.clone().enqueue(this)
    }

    companion object {
        private val TAG = RetryableCallback::class.java.simpleName
    }

    open fun onFinalResponse(call: Call<T>?, response: Response<T>?) {}
    open fun onFinalFailure(call: Call<T>?, t: Throwable?) {}

    init {
        this.totalRetries = totalRetries
    }
}