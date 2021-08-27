package com.leo.base_business.base.net

/**
 * 网络返回的接触
 */
sealed class BusinessBaseResult<out T : Any> {
    val code: Int = 0
    val message: String = ""

    /**
     * 请求成功之后的的数据封装对象bean
     */
    data class Success<out T : Any>(val data: T) : BusinessBaseResult<T>()

    /**
     * 请求失败之后的数据封装对象bean
     */
    data class Error(val errorMsg: String) : BusinessBaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorMsg=$errorMsg]"
        }
    }
}
