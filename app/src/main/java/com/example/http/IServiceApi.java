package com.example.http;

import com.example.bean.AllStories;
import com.example.bean.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IServiceApi {

    @GET("api/4/news/{data}")
    Observable<String> getAllStories(@Path("data") String data);


    @FormUrlEncoded
    @POST("ZJReport/check")
    Observable<Result> postReportData(@FieldMap() Map<String, String> params);

}
