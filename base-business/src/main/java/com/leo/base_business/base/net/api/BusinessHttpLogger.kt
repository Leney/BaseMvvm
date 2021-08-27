package com.leo.base_business.base.net.api

import com.leo.mvvm.utils.DLog
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 网络日志类
 */
class BusinessHttpLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        DLog.d("HttpLog", message)
    }

    /*private val mMessage = StringBuilder()
    private val TAG = "HttpLogger"
    override fun log(message: String) {
        // 请求或者响应开始
        var message = message
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0)
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if (message.startsWith("{") && message.endsWith("}")
            || message.startsWith("[") && message.endsWith("]")
        ) {
            message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message))
        }
        mMessage.append(
            """
                $message
                
                """.trimIndent()
        )
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            DLog.d(TAG, mMessage.toString())
        }
    }*/

}