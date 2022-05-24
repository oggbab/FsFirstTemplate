package com.fsoon.android.fsfirsttemplate.net.model.search

import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase
import com.google.gson.annotations.SerializedName

class ResponseShopSearch : ResponseBase {
    constructor(msg: String?) : super(RESPONSE_FAIL, msg)
    constructor(code: String?, msg: String?) : super(code, msg)

    @SerializedName("total")
    var total //검색 결과 문서의 총 개수를 의미한다.
            = 0

    @SerializedName("start")
    var start //검색 결과 문서 중, 문서의 시작점을 의미한다.
            = 0

    @SerializedName("display")
    var display //검색된 검색 결과의 개수이다.
            = 0

    @SerializedName("items")
    var items = ArrayList<ResponseShopSearchItem>()
}