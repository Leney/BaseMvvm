package com.leo.mvvmdemo.viewmodel

import android.content.Context
import android.widget.Toast
import com.leo.base_business.base.net.checkResult
import com.leo.base_business.base.ui.BusinessViewModel
import com.leo.mvvm.adapter.BaseBindAdapter
import com.leo.mvvm.utils.DLog
import com.leo.mvvmdemo.BR
import com.leo.mvvmdemo.R
import com.leo.mvvmdemo.bean.RecommendVideoBean
import com.leo.mvvmdemo.repository.AppRepository

class RecommendVideoViewModel(private val repository: AppRepository) : BusinessViewModel() {
    private val videoList = mutableListOf<RecommendVideoBean>()
    val adapter by lazy {
        BaseBindAdapter(
            R.layout.adapter_recommend_video_item,
            BR.recommendVideoBean,
            videoList,
            BR.recommendViewModel,
            this
        )
    }

    fun getRecommendVideoList() {
        showLoadingView()
        launchOnIO {
            repository.getRecommendVideoList("127398").checkResult(
                onSuccess = {
                    DLog.i("llj", "请求成功----->>>${it.size}")
                    showContentView()
                    videoList.addAll(it)
                    adapter.notifyDataSetChanged()
                },
                onError = {
                    DLog.e("llj", "获取失败----->>>$it")
                }
            )
        }
    }

    // item 点击事件
    fun itemClick(context: Context, videoBean: RecommendVideoBean) {
        Toast.makeText(context, videoBean.data.author.name, Toast.LENGTH_SHORT).show()
    }
}