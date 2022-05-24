package com.fsoon.android.fsfirsttemplate.net

import android.content.Context
import com.fsoon.android.fsfirsttemplate.net.Service.RestfulService
import com.fsoon.android.fsfirsttemplate.net.model.ResponseBase
import com.fsoon.android.fsfirsttemplate.net.model.ResponseError
import com.fsoon.android.fsfirsttemplate.net.model.goodpay.ResponseAppVersion
import com.fsoon.android.fsfirsttemplate.net.model.search.ResponseShopSearch
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ApiManager private constructor(c: Context) {

    private val fsAPI: FsAPI

    interface OnNetworkListener<T> {
        fun OnNetworkResult(requestId: APIConstants.URL, res: Any)
    }

    private fun setResult(listener: OnNetworkListener<*>?, requestId: APIConstants.URL, res: Any) {
        listener?.OnNetworkResult(requestId, res)
    }

    private fun setResultError(
        listener: OnNetworkListener<*>?,
        requestId: APIConstants.URL,
        res: Any
    ) {
        setResultError(listener, requestId, "", res)
    }

    private fun setResultError(
        listener: OnNetworkListener<*>?,
        requestId: APIConstants.URL,
        sub: String,
        res: Any
    ) {
        listener?.OnNetworkResult(requestId, res)
    }

    fun parseError(response: Response<*>): ResponseError? {
        var error: ResponseError? = null
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        //Converter<ResponseBody, ResponseError> converter = /*retrofit*/RestfulService.getRetrofit().responseBodyConverter(ResponseError.class, new java.lang.annotation.Annotation[0]);
        try {
            //error = converter.convert(response.errorBody());
            val json = JSONObject(response.errorBody()?.string())
            //Logger.e("json  == " + json.toString());
            var retcode = ""
            var retmsg = ""

            if (json.has("retcode")) {
                retcode = json.getString("retcode")
            }

            if (json.has("retmsg")) {
                retmsg = json.getString("retmsg")
            }

            error = ResponseError(
                retcode,
                retmsg
            )
        } catch (e: IOException) {
            error = ResponseError(ResponseBase.RESPONSE_FAIL, e.toString())
        } catch (e: JSONException) {
            error = ResponseError(ResponseBase.RESPONSE_FAIL, e.toString())
        }
        return error
    }

    /*public <T> T parseErrorResponse(Response<?> response, Class<T> classType) throws IOException {
        T error = null;
        if (retrofit != null) {
            Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(classType, new java.lang.annotation.Annotation[0]);
            error = converter.convert(response.errorBody());
        }
        return error;
    }*/
    fun requestSearchShop(
        query: String?,
        start: Int,
        display: Int,
        sort: String?,
        listener: OnNetworkListener<*>?
    ) {
        val service = fsAPI.requestShopSearch(query, start, display, sort)
        service!!.enqueue(object : Callback<ResponseShopSearch?> {
            override fun onResponse(
                call: Call<ResponseShopSearch?>,
                response: Response<ResponseShopSearch?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { setResult(listener, APIConstants.URL.SHOP_SEARCH, it) }
                } else {
                    val error = parseError(response)
                    setResult(
                        listener,
                        APIConstants.URL.SHOP_SEARCH,
                        ResponseShopSearch(error?.resultCode, error?.resultMessage)
                    )
                }
            }

            override fun onFailure(call: Call<ResponseShopSearch?>, t: Throwable) {
                setResultError(
                    listener,
                    APIConstants.URL.SHOP_SEARCH,
                    ResponseShopSearch(t.toString())
                )
            }
        })
    }

    fun requestAppVersion(
        os: String?,
        version: String?,
        packageName: String?,
        listener: OnNetworkListener<*>?
    ) {
        val json = JsonObject()
        json.addProperty("os", os)
        json.addProperty("version", version)
        json.addProperty("packageName", packageName)
        val service = fsAPI!!.requestAppVersion(json.toString())
        service!!.enqueue(object : Callback<ResponseAppVersion?> {
            override fun onResponse(
                call: Call<ResponseAppVersion?>,
                response: Response<ResponseAppVersion?>
            ) {
                if (response.isSuccessful) {
                    response.body()
                        ?.let { setResult(listener, APIConstants.URL.APP_VERSION_INFO, it) }
                } else {
                    val error = parseError(response)
                    setResult(
                        listener,
                        APIConstants.URL.APP_VERSION_INFO,
                        ResponseAppVersion(error?.resultCode, error?.resultMessage)
                    )
                }
            }

            override fun onFailure(call: Call<ResponseAppVersion?>, t: Throwable) {
                setResultError(
                    listener,
                    APIConstants.URL.APP_VERSION_INFO,
                    ResponseAppVersion(t.toString())
                )
            }
        })
    } //    public void requestUpdate(final OnNetworkListener listener) {

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
    companion object {
        private var mInstance: ApiManager? = null
        private lateinit var context: Context

        //if (mInstanse == null) 일때 also{} 호출됨
        fun getInstance(c: Context): ApiManager {
            return mInstance ?: ApiManager(c).also {
                mInstance = it
                context = c
            }
        }
    }

    /**
     * init method
     */
    init { //mCtx = context;
        fsAPI = RestfulService.getInstance(c)
    }
}