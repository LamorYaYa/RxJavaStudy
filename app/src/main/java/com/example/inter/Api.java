package com.example.inter;

import com.example.bean.AllStories;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author master
 * @date 2018/4/9
 */

public interface Api {

    @GET("api/4/news/{data}")
    Observable<AllStories> getAllStories(@Path("data") String data);

}
