package com.yw.formatlogcat;

import com.yw.logcatformatlib.LoggingInterceptor;
import com.yw.logcatformatlib.util.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 制作RetrofitManager用于管理Http请求
 */
public class RetrofitManager {
    private RetrofitManager() {
        initRetrofit();
    }

    private static RetrofitManager instance;

    public synchronized static RetrofitManager getInstance() {
        if (instance == null) {
            instance = new RetrofitManager();
        }
        return instance;
    }

    private Retrofit retrofit;

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        // 日志显示级别
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(1000 * 20, TimeUnit.SECONDS);
        //OkHttp进行添加拦截器loggingInterceptor,只需要添加这一行就行了
        if (Config.IS_DEBUG) {//定义是否开启日志拦截器
            okHttpClient.addInterceptor(new LoggingInterceptor());
        }
        retrofit = new Retrofit.Builder().
                baseUrl("http请求的主路径").
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient.build()).
                build();

    }

    /**
     * 获取Retrofit实例
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
