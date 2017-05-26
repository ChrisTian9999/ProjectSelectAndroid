package com.chris.pss.data.service;

import android.content.Context;

import com.chris.pss.data.interceptor.LoggingInterceptor;
import com.chris.pss.data.interceptor.NetworkStatusInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by noonecode on 2017/5/13.
 */

public class HttpRequest {
    private static final String BASE_URL = "http://192.168.191.1:8080/";
    private static final long DEFAULT_TIMEOUT = 3000;
    private static final long SIZE_OF_CACHE = 20 * 1024 * 1024; // 20 MiB

    protected Retrofit retrofit;
    protected Context mContext;



    //构造方法私有
    protected HttpRequest(Context context) {

        Cache cache = new Cache(new File(context.getCacheDir(), "ok-http-cache"), SIZE_OF_CACHE);
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new NetworkStatusInterceptor(context));
        builder.addNetworkInterceptor(new LoggingInterceptor());
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        builder.cache(cache);
        mContext = context;

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    protected <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
