package com.fsoon.android.fsfirsttemplate.net.model.search;

import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseShopSearchItem extends ResponseBase {

    public ResponseShopSearchItem(String msg) {
        super(RETURN_CODE_999, msg);
    }

    public ResponseShopSearchItem(int code, String msg) {
        super(code, msg);
    }

    @SerializedName("title")
    public String title;          //검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.

    @SerializedName("link")
    public String link;          //검색 결과 문서의 하이퍼텍스트 link를 나타낸다.

    @SerializedName("image")
    public String image;         //썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타난다.

    @SerializedName("lprice")
    public String lprice;            //최저가 정보이다. 최저가 정보가 없는 경우 0으로 표시되며, 가격비교 데이터가 없는 경우 이 필드는 가격을 나타낸다.

    @SerializedName("hprice")
    public String hprice;             //최고가 정보이다. 최고가 정보가 없거나 가격비교 데이터가 없는 경우 0으로 표시된다.

    @SerializedName("mallName")
    public String mallName;        //상품을 판매하는 쇼핑몰의 상호이다. 정보가 없을 경우 네이버로 표기된다.

    @SerializedName("productId")
    public String productId;          //해당 상품에 대한 ID 이다.

    @SerializedName("productType")
    public String productType;        //상품군 정보를 일반상품, 중고상품, 단종상품, 판매예정상품으로 구분한다.

    @SerializedName("maker")
    public String maker;           //해당 상품의 제조사 명이다.

    @SerializedName("brand")
    public String brand;           //해당 상품의 브랜드 명이다.

    @SerializedName("category1")
    public String category1;       //해당 상품의 카테고리, 대분류이다.

    @SerializedName("category2")
    public String category2;       //해당 상품의 카테고리, 중분류이다.

    @SerializedName("category3")
    public String category3;       //해당 상품의 카테고리, 소분류이다.

    @SerializedName("category4")
    public String category4;       //해당 상품의 카테고리, 세분류이다.

}
