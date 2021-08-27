package com.leo.mvvmdemo.repository

import com.leo.base_business.base.net.BusinessBaseResult
import com.leo.base_business.base.repository.BusinessBaseRepository
import com.leo.mvvmdemo.api.AppApiService
import com.leo.mvvmdemo.bean.IPBean
import com.leo.mvvmdemo.bean.RecommendVideoBean

class AppRepository(private val apiService: AppApiService) : BusinessBaseRepository() {
    suspend fun getIP(regsource: String):BusinessBaseResult<IPBean> {
        return apiCall { apiService.getIp(regsource) }
    }

    suspend fun getRecommendVideoList(id: String):BusinessBaseResult<List<RecommendVideoBean>> {
        return apiCall { apiService.getRecommendVideoList(id) }
    }
}