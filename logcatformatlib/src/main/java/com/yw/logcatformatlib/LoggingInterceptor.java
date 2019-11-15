package com.yw.logcatformatlib;

import com.yw.logcatformatlib.util.LogcatReptileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 自定义日志拦截器，输出请求的信息信息以及返回的详细信息
 * create by yangwei
 * on 2019-11-14 20:11
 */
public class LoggingInterceptor implements Interceptor {
    /**
     * 拦截方法
     *
     * @param chain 里面包含了请求的request、返回的response
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) {
        Response response = null;
        try {
            response = LogcatReptileUtil.getInstance().reptileUtil(chain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
