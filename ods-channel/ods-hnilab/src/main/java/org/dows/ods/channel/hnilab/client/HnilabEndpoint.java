package org.dows.ods.channel.hnilab.client;

import org.dows.ods.channel.hnilab.HnilabGetTokenRequest;
import org.dows.ods.channel.hnilab.HnilabResponse;
import org.dows.ods.channel.hnilab.HnilabSaveOperateRecordDataRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface HnilabEndpoint {

    @Headers({"Content-Type:application/json;charset=UTF-8", "Accept:application/json"})
    @POST("/api/OpenData/SaveOperateRecordData")
    Call<HnilabResponse> saveOperateRecordData(@Body HnilabSaveOperateRecordDataRequest hnilabSaveOperateRecordDataRequest);



    @Headers({"Content-Type:application/json;charset=UTF-8", "Accept:application/json"})
    @GET("/api/OpenData/GetUserInfo")
    Call<HnilabResponse> getUserInfo();


    @Headers({"Content-Type:application/json;charset=UTF-8", "Accept:application/json"})
    @POST("/api/OpenData/GetToken")
    Call<HnilabResponse> getToken(@Body HnilabGetTokenRequest hnilabGetTokenRequest);


    @Headers({"Content-Type:application/json;charset=UTF-8", "Accept:application/json"})
    @POST("/api/OpenData/CheckToken")
    Call<HnilabResponse> checkToken();


    /**
     * 构建参数
     *
     * @return
     */
    default String buildSignature() {
        return "";
    }


}
