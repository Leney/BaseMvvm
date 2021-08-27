package com.leo.mvvmdemo.test.fragment

import android.util.Log
import com.leo.base_business.base.net.checkResult
import com.leo.base_business.base.ui.BusinessViewModel
import com.leo.mvvm.expand.getStringMMKV
import com.leo.mvvm.expand.putMMKV
import com.leo.mvvm.utils.DLog
import com.leo.mvvmdemo.repository.AppRepository

class TestFragmentViewModel(private val repository: AppRepository) : BusinessViewModel() {
    fun testFragmentClick() {
//        "s".putMMKV("95949")
//        Log.i("llj","testFragmentClick!!!!---->>>${"s".getStringMMKV()}")

        launchOnIO {
            repository.getRecommendVideoList("127398").checkResult(
                onSuccess = {
                    DLog.i("llj", "size---->>>${it.size}")
                },
                onError = {
                    DLog.e("llj","请求失败----->>>$it")
                }
            )
        }
    }
}