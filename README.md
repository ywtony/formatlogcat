# formatlogcat
Retrofit+RxJava+OkHttp输出网络请求的日志（请求和响应）
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
		![效果图](https://github.com/ywtony/formatlogcat/blob/master/app/logcat2.jpg "参考图")
		![效果图](https://github.com/ywtony/formatlogcat/blob/master/app/logcat2.jpg "参考图")
    
