package com.leo.mvvmdemo.test.fragment

import com.leo.base_business.base.ui.BusinessVMFragment
import com.leo.mvvmdemo.R
import com.leo.mvvmdemo.databinding.FragmentTestBinding

class TestFragment : BusinessVMFragment<TestFragmentViewModel, FragmentTestBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_test

    override fun initView() {
        mViewModel.showContentView()
    }
}