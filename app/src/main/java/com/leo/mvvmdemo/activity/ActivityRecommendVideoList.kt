package com.leo.mvvmdemo.activity

import com.leo.base_business.base.ui.BusinessVMActivity
import com.leo.mvvmdemo.R
import com.leo.mvvmdemo.databinding.ActivityRecommendVideoListBinding
import com.leo.mvvmdemo.viewmodel.RecommendVideoViewModel

class ActivityRecommendVideoList :
    BusinessVMActivity<RecommendVideoViewModel, ActivityRecommendVideoListBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_recommend_video_list

    override fun initView() {
        mViewModel.setTitle("推荐视频")
        mViewModel.getRecommendVideoList()
    }

}