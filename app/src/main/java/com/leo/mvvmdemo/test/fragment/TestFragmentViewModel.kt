package com.leo.mvvmdemo.test.fragment

import android.util.Log
import com.leo.mvvm.expand.getStringMMKV
import com.leo.mvvm.expand.putMMKV
import com.leo.mvvm.vm.BaseViewModel

class TestFragmentViewModel : BaseViewModel() {
    fun testFragmentClick(){
        "s".putMMKV("95949")
        Log.i("llj","testFragmentClick!!!!---->>>${"s".getStringMMKV()}")
    }
}