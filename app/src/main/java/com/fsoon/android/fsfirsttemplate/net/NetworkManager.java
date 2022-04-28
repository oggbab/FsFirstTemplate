package com.fsoon.android.fsfirsttemplate.net;

import static com.fsoon.android.fsfirsttemplate.net.model.ResponseBase.RETURN_CODE_999;

import android.content.Context;

import com.fsoon.android.fsfirsttemplate.net.Service.RestfulService;
import com.fsoon.android.fsfirsttemplate.net.model.ResponseError;
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManager {

    private static NetworkManager mInstance;
    private FsAPI fsAPI;
    private static Context context;

    public interface OnNetworkListener<T> {
        void OnNetworkResult(APIConstants.URL requestId, T res);
    }

    /**
     * Constructor
     */
    public static NetworkManager getInstance(Context c) {
        if (mInstance == null) {
            mInstance = new NetworkManager(c);
        }
        context = c;
        return mInstance;
    }

    /**
     * init method
     */
    private NetworkManager(Context c) { //mCtx = context;
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
//        if (BuildConfig.DEBUG) {
//            Logger.e("Network Error : ", requestId.name() + "_" + sub + " , " + ((ResponseBase) res).getResultCode() + " , " + ((ResponseBase) res).getResultMessage());
//        }
        if (listener != null) {
            /*ResponseBase base = new ResponseBase();
            base.setResultCode(550);
            base.setResultMessage(log);*/
            listener.OnNetworkResult(requestId, res);
        }
    }

    public ResponseError parseError(Response<?> response) {
        /*if (retrofit == null) {
            return new ResponseError(ResponseBase.RETURN_CODE_999, "Network Retrofit Error");
        }*/
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
            int resultCode = 0;
            if (json.has("resultcode")) {
                resultCode = json.getInt("resultcode");
            }
            String resultMessage = "";
            if (json.has("resultmessage")) {
                resultMessage = json.getString("resultmessage");
            }
            String resultOptional = "";
            if (json.has("resultoptional")) {
                resultOptional = json.getString("resultoptional");
            }
            error = new ResponseError(
                    resultCode,
                    resultMessage,
                    resultOptional);
        } catch (IOException | JSONException e) {
            error = new ResponseError(RETURN_CODE_999, e.toString(), "");
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