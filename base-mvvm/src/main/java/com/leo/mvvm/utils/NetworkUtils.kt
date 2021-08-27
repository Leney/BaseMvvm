package com.leo.mvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import com.leo.mvvm.APP_CONTEXT

/**
 * 网络是否为有效网络
 */
fun isNetworkAvailable(): Boolean {
    val manager = APP_CONTEXT.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = manager.activeNetworkInfo
    return !(null == info || !info.isAvailable)
}