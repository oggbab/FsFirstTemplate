package com.fsoon.android.fsfirsttemplate.net;


import static com.fsoon.android.fsfirsttemplate.net.model.ResponseBase.RESPONSE_FAIL;

import android.content.Context;
import com.fsoon.android.fsfirsttemplate.net.Service.RestfulService;
import com.fsoon.android.fsfirsttemplate.net.model.ResponseError;
import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    private static ApiManager mInstance;
    private FsAPI fsAPI;
    private static Context context;

    public interface OnNetworkListener<T> {
        void OnNetworkResult(APIConstants.URL requestId, T res);
    }

    /**
     * Constructor
     */
    public static ApiManager getInstance(Context c) {
        if (mInstance == null) {
            mInstance = new ApiManager(c);
        }
        context = c;
        return mInstance;
    }

    /**
     * init method
     */
    private ApiManager(Context c) { //mCtx = context;
        fsAPI = RestfulService.getInstance(c);
    }

    private void setResult(OnNetworkListener listener, APIConstants.URL requestId, Object res) {
        if (listener != null) {
            listener.OnNetworkResult(requestId, res);
        }
    }

    /**
     * 모듈 초기화
     */
    public void onInit() {
        mInstance = null;
    }

    private void setResultError(OnNetworkListener listener, APIConstants.URL requestId, Object res) {
        setResultError(listener, requestId, "", res);
    }

    private void setResultError(OnNetworkListener listener, APIConstants.URL requestId, String sub, Object res) {
        if (listener != null) {
            listener.OnNetworkResult(requestId, res);
        }
    }

    public ResponseError parseError(Response<?> response) {
        ResponseError error = null;
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        //Converter<ResponseBody, ResponseError> converter = /*retrofit*/RestfulService.getRetrofit().responseBodyConverter(ResponseError.class, new java.lang.annotation.Annotation[0]);
        try {
            //error = converter.convert(response.errorBody());
            JSONObject json = new JSONObject(response.errorBody().string());
            //Logger.e("json  == " + json.toString());
            String retcode = "";
            if (json.has("retcode")) {
                retcode = json.getString("retcode");
            }
            String retmsg = "";
            if (json.has("retmsg")) {
                retmsg = json.getString("retmsg");
            }

            error = new ResponseError(
                    retcode,
                    retmsg);
        } catch (IOException | JSONException e) {
            error = new ResponseError(RESPONSE_FAIL, e.toString());
        }
        return error;
    }
    /*public <T> T parseErrorResponse(Response<?> response, Class<T> classType) throws IOException {
        T error = null;
        if (retrofit != null) {
            Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(classType, new java.lang.annotation.Annotation[0]);
            error = converter.convert(response.errorBody());
        }
        return error;
    }*/

    public void requestSearchShop(String query, int start, int display, String sort, final OnNetworkListener listener) {
        Call<ResponseShopSearch> service = fsAPI.requestShopSearch(query, start, display, sort);
        service.enqueue(new Callback<ResponseShopSearch>() {
            @Override
            public void onResponse(Call<ResponseShopSearch> call, Response<ResponseShopSearch> response) {
                if (response.isSuccessful()) {
                    setResult(listener, APIConstants.URL.SHOP_SEARCH, response.body());
                } else {
                    ResponseError error = parseError(response);
                    setResult(listener, APIConstants.URL.SHOP_SEARCH, new ResponseShopSearch(error.getResultCode(), error.getResultMessage()));
                }
            }

            @Override
            public void onFailure(Call<ResponseShopSearch> call, Throwable t) {
                setResultError(listener, APIConstants.URL.SHOP_SEARCH, new ResponseShopSearch(t.toString()));
            }
        });
    }

    public void requestAppVersion(String os, String version, String packageName, final OnNetworkListener listener) {
        JsonObject json = new JsonObject();
        json.addProperty("os", "AND");
        json.addProperty("version", "01.00.01");
        json.addProperty("packageName", "com.kt.android.goodpay");

        Call<ResponseAppVersion> service = fsAPI.requestAppVersion(json.toString());
        service.enqueue(new Callback<ResponseAppVersion>() {

            @Override
            public void onResponse(Call<ResponseAppVersion> call, Response<ResponseAppVersion> response) {
                if(response.isSuccessful()) {
                    setResult(listener, APIConstants.URL.APP_VERSION_INFO, response.body());
                } else {
                    ResponseError error = parseError(response);
                    setResult(listener, APIConstants.URL.APP_VERSION_INFO, new ResponseAppVersion(error.getResultCode(), error.getResultMessage()));
                }
            }

            @Override
            public void onFailure(Call<ResponseAppVersion> call, Throwable t) {
                setResultError(listener, APIConstants.URL.APP_VERSION_INFO, new ResponseAppVersion(t.toString()));
            }
        });
    }


//    public void requestUpdate(final OnNetworkListener listener) {
//        Call<ResponseUpdate> service = fsAPI.requestUpdate(APIConstants.URL_UPDATEINFO);
//        service.enqueue(new Callback<ResponseUpdate>() {
//            @Override
//            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
//                if (response.isSuccessful()) {
//                    setResult(listener, APIConstants.URL.UPDATEINFO, response.body());
//                } else {
//                    ResponseError error = parseError(response);
//                    setResult(listener, APIConstants.URL.UPDATEINFO, new ResponseUpdate(error.getResultCode(), error.getResultMessage()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
//                setResultError(listener, APIConstants.URL.MOVIE_RELATED_CONTENTS, new ResponseUpdate(t.toString()));
//            }
//        });
//    }


}