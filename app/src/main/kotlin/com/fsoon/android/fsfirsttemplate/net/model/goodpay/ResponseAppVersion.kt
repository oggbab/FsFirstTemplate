package com.fsoon.android.fsfirsttemplate.net.model.goodpay

import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase
import com.google.gson.annotations.SerializedName

class ResponseAppVersion : ResponseBase {
    constructor(msg: String?) : super(RESPONSE_FAIL, msg) {}
    constructor(code: String?, msg: String?) : super(code, msg) {}

    @kotlin.jvm.JvmField
    @SerializedName("data")
    var data: ResponseAppVersionData? = null

    inner class ResponseAppVersionData {
        @kotlin.jvm.JvmField
        @SerializedName("appVersion")
        var appVersion: String? = null

        @SerializedName("mandatory")
        var mandatory: String? = null
    }
}