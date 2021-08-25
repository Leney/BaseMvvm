package com.leo.mvvm.bean

enum class BaseStatus {
    BASE_STATUS_CONTENT, // 显示子页面视图
    BASE_STATUS_LOADING, // 显示加载视图
    BASE_STATUS_ERROR, // 显示错误视图
    BASE_STATUS_EMPTY, // 显示空视图
    BASE_STATUS_TOAST     // 弹Toast
}