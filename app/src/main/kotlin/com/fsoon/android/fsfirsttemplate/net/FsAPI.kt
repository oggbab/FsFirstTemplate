package com.fsoon.android.fsfirsttemplate.net

import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch
import retrofit2.Call
import retrofit2.http.*

interface FsAPI {
    /**
     * 네이버 쇼핑 검색
     */
    @GET(APIConstants.URL_SHOP_SEARCH)
    fun requestShopSearch(
        @Query("query") query: String?,
        @Query("start") limit: Int,
        @Query("display") display: Int,
        @Query("sort") sort: String?
    ): Call<ResponseShopSearch?>?

    /**
     * 착한페이 AppVersion 조회
     * @param body
     * @return
     */
    @POST(APIConstants.URL_APP_VERSION_INFO)
    @Headers("Content-Type:application/json")
    fun requestAppVersion(
        @Body body: String?
    ): Call<ResponseAppVersion?>? //    @GET(APIConstants.URL_SEARCH_KEYWORD)
    //    Call<ArrayList<ResponseSearchInstance>> requestSearchKeywords(
    //            @Path(APIConstants.TYPE) String type,
    //            @Query(APIConstants.KEYWORD) String keyword
    //    );
    //
}