package com.yw.logcatformatlib.util;

import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 输出Http请求的请求日志和响应日志
 * create by yangwei
 * on 2019-11-15 11:26
 */
public class LogcatReptileUtil {
    private LogcatReptileUtil() {
    }

    private static LogcatReptileUtil instance;

    public synchronized static LogcatReptileUtil getInstance() {
        if (instance == null) {
            instance = new LogcatReptileUtil();
        }
        return instance;
    }

    /**
     * 日志抓包
     *
     * @param chain
     */
    public Response reptileUtil(Interceptor.Chain chain) throws Exception {

        //日志要输出的内容
        /*
            1.请求类型+请求的url
            2.内容类型
            3.内容长度
            4.host+端口
            5.链接类型
            6.Accept-Encoding
            7.User-Agent
            8.请求参数

            9.响应码
            10.响应内容
         */

        //输出请求内容
        Request request = chain.request();
        long createTime = System.nanoTime();//开始时间
        String method = request.method();
        HttpUrl httpUrl = request.url();
        RequestBody requestBody = request.body();
        DevLog.e(method + "  " + httpUrl.url().toString());//输出请求方式和接口地址
        DevLog.e("Content-Type", requestBody.contentType().toString());
        DevLog.e("Content-Length", requestBody.contentLength() + "");
        DevLog.e("host:port", httpUrl.host() + ":" + httpUrl.port());
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        String params = buffer.readString(Charset.forName("UTF-8"));
        DevLog.e("params", ParamsUtil.getFormatParams(params));


        //返回内容
        Response response = chain.proceed(request);
        long endTime = System.nanoTime();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        //格式化输出服务端返回的数据
        DevLog.e("Response-url", response.request().url() + "");
        DevLog.e("Response-Code", response.code() + "");
        DevLog.e("Response-Time", (endTime - createTime) / 1e6d + "");
        DevLog.e("Response-Content", LogcatJsonFormat.getInstance().format(responseBody.string()));
        return response;
    }
}
