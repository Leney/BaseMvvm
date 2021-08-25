package com.leo.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Fragment 基类
 */
abstract class BaseFragment : Fragment() {
    private var rootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return if (!isDataBindView()) {
            rootView = inflater.inflate(getLayoutId(), container, false)
            rootView
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(rootView)
        initData(rootView)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    protected open fun initView(rootView: View?){}
    protected open fun initData(rootView: View?) {}

    /**
     * 是否是DataBind界面
     */
    protected open fun isDataBindView(): Boolean = false
}