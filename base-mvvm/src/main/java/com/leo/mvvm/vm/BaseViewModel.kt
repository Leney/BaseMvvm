package com.leo.mvvm.vm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leo.mvvm.APP_CONTEXT
import com.leo.mvvm.bean.BaseStatus
import com.leo.mvvm.bean.BaseStatusBean
import com.leo.mvvm.bean.BaseTitleBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MVVM ViewModel基类
 */
abstract class BaseViewModel : ViewModel() {
    /**
     * UI协程中执行
     */
    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) { block() }
    }

    /**
     * IO协程执行
     */
    fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) { block() }
    }

    /**
     * 网络错误重新加载事件
     */
    var netErrorClick: View.OnClickListener? = null

    /**
     * 标题视图绑定数据
     */
    val baseTitleModel = BaseTitleBean()

    /**
     * 当前界面显示视图类型状态
     */
    val baseStatus = MutableLiveData(BaseStatusBean(BaseStatus.BASE_STATUS_LOADING))

    /**
     * 设置标题名称
     */
    fun setTitle(resId:Int){
        baseTitleModel.setTitle(APP_CONTEXT.resources.getString(resId))
    }

    /**
     * 设置标题名称
     */
    fun setTitle(titleName:String){
        baseTitleModel.setTitle(titleName)
    }

    /**
     * 显示内容视图
     */
    fun showContentView() {
        baseStatus.postValue(BaseStatusBean(BaseStatus.BASE_STATUS_CONTENT))
    }

    /**
     * 显示正在加载视图
     */
    fun showLoadingView() {
        baseStatus.postValue(BaseStatusBean(BaseStatus.BASE_STATUS_LOADING))
    }

    /**
     * 显示错误视图
     */
    fun showErrorView() {
        baseStatus.postValue(BaseStatusBean(BaseStatus.BASE_STATUS_ERROR))
    }

    /**
     * 显示错误视图(附带点击重试事件)
     */
    fun showErrorViewByClick(listener: View.OnClickListener) {
        showErrorView()
        netErrorClick = listener
    }

    /**
     * 显示空视图
     */
    fun showDataEmpty() {
        baseStatus.postValue(BaseStatusBean(BaseStatus.BASE_STATUS_EMPTY))
    }
}