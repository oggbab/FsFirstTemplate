package com.fsoon.android.fsfirsttemplate.net.model.goodpay;

import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearchItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseAppVersion extends ResponseBase {

    public ResponseAppVersion(String msg) {
        super(RESPONSE_FAIL, msg);
    }

    public ResponseAppVersion(String code, String msg) {
        super(code, msg);
    }

    @SerializedName("data")
    public ResponseAppVersionData data;

    public class ResponseAppVersionData {
        @SerializedName("appVersion")
        public String appVersion;

        @SerializedName("mandatory")
        public String mandatory;
    }
}
