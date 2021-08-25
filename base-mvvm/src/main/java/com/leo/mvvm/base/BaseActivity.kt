package com.leo.mvvm.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity 基类
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!isDataBindView()){
            // 如果不是DataBind界面
            setContentView(getLayoutId())
            initView()
            initData()
        }
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun initView()
    protected open fun initData(){}

    /**
     * 是否是DataBind界面
     */
    protected open fun isDataBindView():Boolean = false
}