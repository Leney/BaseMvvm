package com.leo.base_business.base.net

import com.leo.base_business.base.net.BusinessBaseResult
import com.leo.mvvm.utils.DLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// BusinessBaseResult 网络返回结果封装对象的扩展类
/**
 * 回调处理会在主线程中处理的扩展函数(在主线程中处理)
 * @param onSuccess 请求成功的处理方法
 * @param onError 请求失败的处理方法
 */
suspend inline fun <T : Any> BusinessBaseResult<T>.checkResult(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (String?) -> Unit,
) {
    if (this is BusinessBaseResult.Success) {
        withContext(Dispatchers.Main) {
            onSuccess(data)
        }
    } else if (this is BusinessBaseResult.Error) {
        DLog.e("BusinessBaseResult","errorMsg--->>>$errorMsg")
        withContext(Dispatchers.Main) {
            onError(errorMsg)
        }
    }
}

/**
 * 回调处理会在主线程中处理的扩展函数，但失败的时候会返回失败错误code(在主线程中处理)
 * @param onSuccess 请求成功的处理方法
 * @param onError 请求失败的处理方法
 */
suspend inline fun <T : Any> BusinessBaseResult<T>.checkResult2(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (String?, Int?) -> Unit,
) {
    if (this is BusinessBaseResult.Success) {
        withContext(Dispatchers.Main) {
            onSuccess(data)
        }

    } else if (this is BusinessBaseResult.Error) {
        DLog.e("BusinessBaseResult","errorMsg--->>>$errorMsg")
        withContext(Dispatchers.Main) {
            onError(errorMsg, code)
        }
    }
}

/**
 * 回调处理会在异步线程中处理的扩展函数(不会切换到主线程)
 * @param onSuccess 请求成功的处理方法
 * @param onError 请求失败的处理方法
 */
suspend inline fun <T : Any> BusinessBaseResult<T>.checkResultByAsyn(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (String?) -> Unit,
) {
    if (this is BusinessBaseResult.Success) {
        onSuccess(data)
    } else if (this is BusinessBaseResult.Error) {
        DLog.e("BusinessBaseResult","errorMsg--->>>$errorMsg")
        onError(errorMsg)
    }
}

/**
 * 回调处理会在异步线程中处理的扩展函数，但失败的时候会返回失败错误code(不会切换到主线程)
 * @param onSuccess 请求成功的处理方法
 * @param onError 请求失败的处理方法
 */
suspend inline fun <T : Any> BusinessBaseResult<T>.checkResultByAsyn2(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (String?, Int?) -> Unit,
) {
    if (this is BusinessBaseResult.Success) {
        withContext(Dispatchers.Main) {
            onSuccess(data)
        }

    } else if (this is BusinessBaseResult.Error) {
        DLog.e("BusinessBaseResult","errorMsg--->>>$errorMsg")
        withContext(Dispatchers.Main) {
            onError(errorMsg, code)
        }
    }
}