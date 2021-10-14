package com.leo.base_business

import com.leo.mvvm.BaseApplication
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

open class BusinessApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            BezierRadarHeader(context)
        }
    }
}