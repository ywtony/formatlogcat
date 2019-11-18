# formatlogcat
Retrofit+RxJava+OkHttp输出网络请求的日志（请求和响应）
框架的主要作用是自定义日志输出，可以把网络请求的接口地址、请求类型、请求参数输出到Logcat上，也可以把响应的的状态、响应时间以及响应内容输出到Logcat上。在没有抓包工具的情况下依然能够一目了然的看到网络请求和响应的详细信息，方便定位以及调试问题。
1.需要两步进行配置:
第一步：在项目主配置文件中添加如下代码

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
第二步：在App目录中的gradle文件中引入

	dependencies {
	        implementation 'com.github.ywtony:formatlogcat:v0.0.2'
	}

2.使用方法：

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
		
   3.效果如下图所示：
		![效果图](https://github.com/ywtony/formatlogcat/blob/master/app/logcat1.jpg "参考图")
		![效果图](https://github.com/ywtony/formatlogcat/blob/master/app/logcat2.jpg "参考图")
    
