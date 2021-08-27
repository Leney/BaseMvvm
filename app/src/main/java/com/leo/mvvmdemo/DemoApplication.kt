package com.leo.mvvmdemo

import com.leo.base_business.BusinessApplication
import org.koin.core.context.loadKoinModules

class DemoApplication : BusinessApplication() {
    override fun onCreate() {
        super.onCreate()
        // 加载koin需要注入的module数据
        loadKoinModules(appModules)
    }
}