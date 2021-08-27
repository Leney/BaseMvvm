package com.leo.mvvmdemo.api

import com.leo.base_business.bean.BusinessResponseBean
import com.leo.mvvmdemo.bean.IPBean
import com.leo.mvvmdemo.bean.RecommendVideoBean
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AppApiService {
    @POST("ip/get")
    suspend fun getIp(@Query("regsource") regsource:String):BusinessResponseBean<IPBean>

    @GET("videoRecommend")
    suspend fun getRecommendVideoList(@Query("id") id:String):BusinessResponseBean<List<RecommendVideoBean>>
}