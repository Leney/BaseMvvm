package com.leo.base_business.base.net.api

import com.leo.base_business.BASE_URL
import com.leo.base_business.BuildConfig
import com.leo.mvvm.APP_CONTEXT
import com.leo.mvvm.utils.isNetworkAvailable
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 基础 RetrofitClient
 */
class BusinessRetrofitClient {

    companion object {
        private const val TIME_OUT = 5

        @JvmStatic
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { BusinessRetrofitClient() }
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            handleBuilder(builder)
            val logging = HttpLoggingInterceptor(BusinessHttpLogger())
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }
            builder.addInterceptor(logging)
            return builder.build()
        }

    private fun handleBuilder(builder: OkHttpClient.Builder) {
        val httpCacheDirectory = File(APP_CONTEXT.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!isNetworkAvailable()) {
                    request = request.newBuilder()
                        // CacheControl.FORCE_CACHE:强制使用缓存,如果没有缓存数据,则抛出504(only-if-cached)
                        // CacheControl.FORCE_NETWORK:强制使用网络,不使用任何缓存.
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build()
                }
                val response = chain.proceed(request)
                if (!isNetworkAvailable()) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }
                response
            }
        // 这里可添加拦截器
//        builder.addInterceptor()
    }

    fun <S> getService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(BASE_URL)
            .build().create(serviceClass)
    }
}
