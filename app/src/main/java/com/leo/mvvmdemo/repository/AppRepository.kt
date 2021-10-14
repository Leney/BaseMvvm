package com.leo.mvvmdemo.repository

import com.leo.base_business.JISU_API_KEY
import com.leo.base_business.base.net.BusinessBaseResult
import com.leo.base_business.base.repository.BusinessBaseRepository
import com.leo.mvvmdemo.api.AppApiService
import com.leo.mvvmdemo.bean.IPBean
import com.leo.mvvmdemo.bean.ChannelNews
import com.leo.mvvmdemo.bean.RecommendVideoBean

class AppRepository(private val apiService: AppApiService) : BusinessBaseRepository() {
    suspend fun getIP(regsource: String): BusinessBaseResult<IPBean> {
        return apiCall { apiService.getIp(regsource) }
    }

    suspend fun getRecommendVideoList(id: String): BusinessBaseResult<List<RecommendVideoBean>> {
        return apiCall { apiService.getRecommendVideoList(id) }
    }

    /**
     * 根据频道获取新闻列表信息
     */
    suspend fun getNewsListByType(
        channel: String,
        start: Int,
        num: Int,
    ): BusinessBaseResult<ChannelNews> {
        val params = mutableMapOf<String,Any>().apply {
            put("channel",channel)
            put("start",start)
            put("num",num)
            put("appkey",JISU_API_KEY)
        }

//        val params = mapOf(
//            Pair("channel", channel),
//            Pair("start", start),
//            Pair("num", num),
//            Pair("appkey", JISU_API_KEY)
//        )
        return apiCall { apiService.getNewsListByType(params) }
    }
}