package com.example.http;

import android.util.Log;

import com.example.MyApplication;
import com.example.string.StringConverterFactory;
import com.example.util.ApiUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Master
 */
public class RetrofitApi {


//    public static class HttpParamsInterceptor implements Interceptor {
//
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            //获取到request
//            Request request = chain.request();
//            //获取到方法
//            String method = request.method();
//            try {
//
//                //添加公共参数
//                HashMap<String, Object> commomParamsMap = new HashMap<>();
//                commomParamsMap.put("", "");
//
//                if (method.equals("GET")) {
//                    HttpUrl url = request.url();
//                    HttpUrl.Builder newUrlBuilder = url.newBuilder();
//                    for (String key : commomParamsMap.keySet()) {
//                        newUrlBuilder.addEncodedQueryParameter(key, commomParamsMap.get(key) + "");
//                    }
//                    HttpUrl newUrl = newUrlBuilder.build();
//                    request = request.newBuilder().url(newUrl).build();
//                } else if (method.equals("POST")) {
//                    // 得到请求体
//                    RequestBody body = request.body();
//                    // 创建缓存
//                    Buffer buffer = new Buffer();
//                    //将请求体内容,写入缓存
//                    body.writeTo(buffer);
//                    // 读取参数字符串
//                    String parameterStr = buffer.readUtf8();
//                    // TODO 如果是json串就解析 重新加参 如果是字符串就进行修改 具体业务逻辑确认后实现
//
//                    // TODO 对应请求头大伙按照自己的传输方式 定义
//                    RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), parameterStr);
//                    request = request.newBuilder().patch(requestBody).build();
//                }
//
//            } catch (Throwable t) {
//            }
//            return chain.proceed(request);
//        }
//    }


    private static OkHttpClient mOkHttpClient;

    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient != null) {
            return mOkHttpClient;
        }


        return mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
//                .addInterceptor(new HttpParamsInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();
    }


    // 创建网络接口请求实例
    public static <T> T createApi(Class<T> service) {

        Gson gson = new GsonBuilder().setLenient().create();
        GsonConverterFactory mGsonConverterFactory = GsonConverterFactory.create(gson);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtil.ZHI_HU_URL)
                .client(getOkHttpClient())
//                .addConverterFactory(mGsonConverterFactory)
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }


    public static <T> void request(Observable<T> observable, final IResponseListener<T> listener) {

        if (!NetUtils.isConnected(MyApplication.getApplication())) {
            ToastUtils.getInstance().showToast("网络不可用,请检查网络");
            if (listener != null) {
                listener.onFail();
            }
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("社会我伟哥", e.getMessage());
                                   if (listener != null) {
                                       listener.onFail();
                                   }
                               }

                               @Override
                               public void onComplete() {

                               }

                               @Override
                               public void onSubscribe(Disposable disposable) {

                               }

                               @Override
                               public void onNext(T data) {
                                   if (listener != null) {
                                       listener.onSuccess(data);
                                   }
                               }
                           }
                );
    }

    public interface IResponseListener<T> {
        void onSuccess(T data);

        void onFail();
    }

}
