package com.leo.base_business.bean

import com.google.gson.annotations.SerializedName

/**
 * 网络返回数据Bean对象
 * 默认返回数据格式：{"code":0,"msg":"xxx","data":{xxx}}
 */
data class BusinessResponseBean<out T>(
    val code: Int,
    @SerializedName("message") val msg: String,
    @SerializedName("result") val data: T,
)