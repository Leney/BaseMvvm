package com.leo.mvvmdemo.viewmodel

import android.content.Context
import android.widget.Toast
import com.leo.base_business.base.net.checkResult
import com.leo.base_business.base.ui.BusinessViewModel
import com.leo.mvvm.adapter.BaseBindAdapter
import com.leo.mvvmdemo.BR
import com.leo.mvvmdemo.R
import com.leo.mvvmdemo.bean.NewsInfo
import com.leo.mvvmdemo.bean.RecommendVideoBean
import com.leo.mvvmdemo.repository.AppRepository

class RecommendVideoViewModel(private val repository: AppRepository) : BusinessViewModel() {
    //    private val videoList = mutableListOf<RecommendVideoBean>()
    private val newsList = mutableListOf<NewsInfo>()
//    val adapter by lazy {
//        BaseBindAdapter(
//            R.layout.adapter_recommend_video_item,
//            BR.recommendVideoBean,
//            videoList,
//            BR.recommendViewModel,
//            this
//        )
//    }

    val adapter by lazy {
        BaseBindAdapter(
            R.layout.adapter_recommend_news_item,
            BR.newsInfo,
            newsList,
            BR.recommendViewModel,
            this
        )
    }

    // 多item的布局列表
//    private val itemViewTypeList =
//        listOf(R.layout.adapter_recommend_video_item, R.layout.adapter_recommend_video_item_2)

//    // 多item type Adapter
//    val multiAdapter by lazy {
//        BaseMultiBindAdapter(
//            videoList,
//            BR.recommendVideoBean,
//            itemViewTypeList,
//            BR.recommendViewModel,
//            this)
//    }

    fun getRecommendVideoList() {
        launchOnIO {
//            repository.getRecommendVideoList("127398").checkResult(
//                onSuccess = { videos ->
//                    DLog.i("llj", "请求成功----->>>${videos.size}")
//                    for (index in videos.indices) {
//                        if (index % 2 == 0) {
//                            videos[index].itemType = itemViewTypeList[0]
//                        } else {
//                            videos[index].itemType = itemViewTypeList[1]
//                        }
//                    }
//                    showContentView()
//                    videoList.addAll(videos)
////                    multiAdapter.notifyDataSetChanged()
//                },
//                onError = {
//                    DLog.e("llj", "获取失败----->>>$it")
//                    showErrorViewByClick {
//                        showLoadingView()
//                        getRecommendVideoLhttps://api.jisuapi.comist()
//                    }
//                }
//            )

            repository.getNewsListByType("头条", 0, 10).checkResult(
                onSuccess = {
                    showContentView()
                    newsList.addAll(it.list)
                },
                onError = {
                    showErrorViewByClick {
                        showLoadingView()
                        getRecommendVideoList()
                    }
                }
            )
        }
    }

    // item 点击事件
    fun itemClick(context: Context, videoBean: RecommendVideoBean) {
        Toast.makeText(context, videoBean.data.author.name, Toast.LENGTH_SHORT).show()
    }
}