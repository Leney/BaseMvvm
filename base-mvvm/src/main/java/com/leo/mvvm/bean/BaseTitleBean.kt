package com.leo.mvvm.bean

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.leo.mvvm.R

/**
 * 统一标题Bean
 */
class BaseTitleBean {
    // 是否显示整个标题
    val isTitleVisible = ObservableBoolean(true)

    // 返回按钮图片资源id
    val leftIconRes = ObservableInt(R.drawable.base_ic_back)

    // 左边按钮是否显示
    val isLeftIconVisible = ObservableBoolean(true)

    // 标题名称
    val titleName = ObservableField("")

    // 返回按钮点击事件
    var leftIconClickListener = View.OnClickListener {
        // 默认点击事件是关闭当前界面
        if (it.context is Activity) {
            (it.context as Activity).finish()
        }
    }

    /**
     * 返回按钮点击事件
     */
    fun setLeftIconClickListener(listener: View.OnClickListener): BaseTitleBean {
        leftIconClickListener = listener
        return this
    }

    /**
     * 设置标题名称
     */
    fun setTitle(name: String): BaseTitleBean {
        titleName.set(name)
        return this
    }

    /**
     * 设置左边按钮是否显示
     */
    fun setLeftIconVisible(visible: Boolean): BaseTitleBean {
        isLeftIconVisible.set(visible)
        return this
    }

    /**
     * 设置左边按钮显示资源id
     */
    fun setLeftIconRes(res: Int): BaseTitleBean {
        leftIconRes.set(res)
        return this
    }


}