package com.leo.mvvmdemo.test

import com.leo.mvvm.vm.BaseVMActivity
import com.leo.mvvmdemo.R
import com.leo.mvvmdemo.databinding.ActivityTestBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : BaseVMActivity<TestViewModel, ActivityTestBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_test

    val testViewModel: TestViewModel by viewModel()
    override fun initView() {
        mViewModel.baseTitleModel.setTitle("这是测试界面").setLeftIconClickListener {
            mViewModel.showErrorViewByClick {
                mViewModel.testKoin()
            }
        }

        mViewModel.showContentView()
    }

//    override fun getErrorLayout(): BaseErrorViewBean {
//        // 自定义错误视图测试
//        return BaseErrorViewBean(R.layout.layout_test_error, BR.errorModel)
//    }
}