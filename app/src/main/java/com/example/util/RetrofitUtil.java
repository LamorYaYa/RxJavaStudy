package com.example.util;


import com.example.inter.Api;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author master
 * @date 2018/5/4
 */

public class RetrofitUtil {

    private Api mHttpService = null;

    private RetrofitUtil() {
        init();
    }

    public static class RetrofitUtilHolder {
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    public static RetrofitUtil getInstances() {
        return RetrofitUtilHolder.INSTANCE;
    }

    private void init() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtil.ZHI_HU_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mHttpService = retrofit.create(Api.class);
    }

    public Api getService() {
        return mHttpService;
    }
}
