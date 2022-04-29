package com.fsoon.android.fsfirsttemplate.net;

public class APIConstants {
    public static final String NAVER_CLIENT_ID = "8KkD6sXBIwOKaxtwXr9K";
    public static final String NAMER_CLIENT_SECRET = "4sUuJdfvdT";

//    public static final String BASE_URL = "https://openapi.naver.com/v1/";
    public static final String BASE_URL = "http://210.179.57.140/";

    // 검색
    public static final String URL_SHOP_SEARCH = "search/shop.json";   // 검색결과 목록
    public static final String URL_SEARCH_KEYWORD = "search/{type}/keywords";   // 자동완성 검색 키워드, 인기, 추천 검색어
    public static final String URL_APP_VERSION_INFO = "localpay/mobile/main/getVersionInfo";

    public enum URL {
        BASE {
            public String toString() {
                return BASE_URL;
            }
        },
        SHOP_SEARCH {
            public String toString() {
                return URL_SHOP_SEARCH;
            }
        },
        APP_VERSION_INFO {
            public String toString() { return URL_APP_VERSION_INFO; }
        }
    }
}