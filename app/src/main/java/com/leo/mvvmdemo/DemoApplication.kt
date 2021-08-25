package com.leo.mvvmdemo

import com.leo.mvvm.BaseApplication
import org.koin.core.context.loadKoinModules

class DemoApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        // 加载koin需要注入的module数据
        loadKoinModules(appModules)
    }
}