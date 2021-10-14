package com.leo.base_business.expand

import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * desc   : 下拉刷新控件扩展函数
 * date   : 2020/09/16
 * version: 1.0
 */
/**
 * 绑定下拉刷新事件
 * @receiver SmartRefreshLayout
 * @param action Function0<Unit>?
 */
@BindingAdapter("bindOnRefreshListener")
fun SmartRefreshLayout.bindOnRefreshListener(action: (() -> Unit)?) {
    if (action == null) return
    setOnRefreshListener {
        action()
    }
}

/**
 * 绑定加载下一页数据事件
 * @receiver SmartRefreshLayout
 * @param action Function0<Unit>?
 */
@BindingAdapter("bindOnLoadMoreListener")
fun SmartRefreshLayout.bindOnLoadMoreListener(action: (() -> Unit)?) {
    if (action == null) return
    setOnLoadMoreListener {
        action()
    }
}

/**
 * 绑定下拉刷新完成处理
 * @receiver SmartRefreshLayout
 * @param isSuccess Boolean?
 */
@BindingAdapter("bindIsRefreshSuccess")
fun SmartRefreshLayout.bindIsRefreshSuccess(isSuccess: Boolean?) {
    if (isSuccess == null) return
    finishRefresh(isSuccess)
}

/**
 * 绑定加载更多完成处理
 * @receiver SmartRefreshLayout
 * @param isSuccess Boolean?
 * @param isHasMoreData Boolean? 是否还有下一页数据
 */
@BindingAdapter("bindIsLoadMoreSuccess", "bindIsHasMoreData", requireAll = false)
fun SmartRefreshLayout.bindIsLoadMoreSuccess(isSuccess: Boolean?, isHasMoreData: Boolean?) {
    if (isSuccess != null) {
        finishLoadMore(isSuccess)
    }
    if (isHasMoreData != null && !isHasMoreData) {
        // 没有更多数据了
//        setEnableFooterFollowWhenNoMoreData(false)
        finishLoadMoreWithNoMoreData()
    }
}

/**
 * 是否开启下来刷新
 * @receiver SmartRefreshLayout
 * @param isEnableRefresh Boolean
 */
@BindingAdapter("bindIsEnableRefresh")
fun SmartRefreshLayout.isEnableRefresh(isEnableRefresh: Boolean) {
    setEnableRefresh(isEnableRefresh)
}


/**
 * 是否开启上拉加载
 */
@BindingAdapter("bindIsEnableLoadMore")
fun SmartRefreshLayout.isEnableLoadMore(isEnableLoadMore: Boolean) {
    setEnableLoadMore(isEnableLoadMore)
}

///**
// * 设置是否在没有更多数据之后 Footer 跟随内容
// */
//@BindingAdapter("bindIsEnableFooterFollowWhenNoMoreData")
//fun SmartRefreshLayout.isEnableFooterFollowWhenNoMoreData(isEnableFooterFollowWhenNoMoreData: Boolean?) {
//    if (isEnableFooterFollowWhenNoMoreData == null) return
//    setEnableFooterFollowWhenNoMoreData(isEnableFooterFollowWhenNoMoreData)
//}