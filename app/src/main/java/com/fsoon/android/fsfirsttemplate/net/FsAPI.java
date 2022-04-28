package com.fsoon.android.fsfirsttemplate.net;

import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FsAPI {

     /**
     * 네이버 쇼핑 검색
     */
    @GET(APIConstants.URL_SHOP_SEARCH)
    Call<ResponseShopSearch> requestShopSearch(
            @Query("query") String query,
            @Query("start") int limit,
            @Query("display") int display,
            @Query("sort") String sort
    );

//    @GET(APIConstants.URL_SEARCH_KEYWORD)
//    Call<ArrayList<ResponseSearchInstance>> requestSearchKeywords(
//            @Path(APIConstants.TYPE) String type,
//            @Query(APIConstants.KEYWORD) String keyword
//    );
//
}