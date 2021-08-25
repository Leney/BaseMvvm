package com.leo.mvvmdemo.test.fragment

import android.util.Log
import com.leo.mvvm.vm.BaseViewModel

class TestFragmentViewModel : BaseViewModel() {
    fun testFragmentClick(){
        Log.i("llj","testFragmentClick!!!!")
    }
}