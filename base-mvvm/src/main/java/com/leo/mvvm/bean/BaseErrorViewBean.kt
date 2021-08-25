package com.leo.mvvm.bean

/**
 * 自定义错误视图，所需要传入的bean对象
 * @param layoutId 对应的布局id
 * @param brId 布局对应的ViewModel id(即：name属性的值)
 */
data class BaseErrorViewBean(val layoutId: Int, val brId: Int = 0)
