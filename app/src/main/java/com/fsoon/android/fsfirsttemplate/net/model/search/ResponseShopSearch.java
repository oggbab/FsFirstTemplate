package com.fsoon.android.fsfirsttemplate.net.model.search;

import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseShopSearch extends ResponseBase {

    public ResponseShopSearch(String msg) {
        super(RETURN_CODE_999, msg);
    }

    public ResponseShopSearch(int code, String msg) {
        super(code, msg);
    }

    @SerializedName("total")
    public int total;          //검색 결과 문서의 총 개수를 의미한다.

    @SerializedName("start")
    public int start;          //검색 결과 문서 중, 문서의 시작점을 의미한다.

    @SerializedName("display")
    public int display;        //검색된 검색 결과의 개수이다.

    @SerializedName("items")
    public ArrayList<ResponseShopSearchItem> items = new ArrayList<>();

//    public ResponseShopSearchChannel channel;

//    public class ResponseShopSearchChannel {
//        @SerializedName("total")
//        public int total;          //검색 결과 문서의 총 개수를 의미한다.
//
//        @SerializedName("start")
//        public int start;          //검색 결과 문서 중, 문서의 시작점을 의미한다.
//
//        @SerializedName("display")
//        public int display;        //검색된 검색 결과의 개수이다.
//
//        @SerializedName("items")
//        public ArrayList<ResponseShopSearchItem> items = new ArrayList<>();
//    }
}
