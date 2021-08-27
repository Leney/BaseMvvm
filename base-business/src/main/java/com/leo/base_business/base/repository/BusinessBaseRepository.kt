package com.leo.base_business.base.repository

import com.leo.base_business.base.net.BusinessBaseResult
import com.leo.base_business.bean.BusinessResponseBean
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

/**
 * Repository(责任) 基类
 */
open class BusinessBaseRepository {
    // 网络接口相关处理
    /**
     * 协程中处理网络请求
     * @param call 需要网络调用的请求接口并返回BusinessResponseBean<T>的对象
     * @return 封装之后的网络返回结果数据
     */
    suspend fun <T : Any> apiCall(call: suspend () -> BusinessResponseBean<T>): BusinessBaseResult<T> {
        return try {
            execute(call())
        } catch (e: Exception) {
            // 请求返回结果处理异常
            BusinessBaseResult.Error(e.message ?: "")
        }
    }

    /**
     * 执行网络请求
     * @param response 网络请求返回的BusinessResponseBean<T> 对象
     */
    private suspend fun <T : Any> execute(response: BusinessResponseBean<T>): BusinessBaseResult<T> {
        return coroutineScope {
            if (response.code == 200) {
                // 请求成功
                // 返回请求成功的封装对象
                BusinessBaseResult.Success(response.data)
            } else {
                // 请求失败
                // 返回请求失败的封装对象
                BusinessBaseResult.Error(response.msg)
            }
        }
    }
}