package com.leo.mvvmdemo.test.fragment

import com.leo.mvvm.vm.BaseVmFragment
import com.leo.mvvmdemo.R
import com.leo.mvvmdemo.databinding.FragmentTestBinding

class TestFragment : BaseVmFragment<TestFragmentViewModel, FragmentTestBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_test

    override fun initView() {
        mViewModel.showContentView()
    }
}