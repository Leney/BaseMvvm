package com.leo.mvvmdemo.test

import android.util.Log
import android.view.View
import com.leo.mvvm.vm.BaseViewModel
import com.leo.mvvmdemo.repository.HelloRepository

class TestViewModel(val repo: HelloRepository) : BaseViewModel() {
    val testClick = View.OnClickListener {
        Log.i("llj", "测试自定义错误视图点击事件！！！！")
    }

    fun testKoin() {
        Log.i("llj", "这是消息---->>>${repo.giveHello()}")
    }
}