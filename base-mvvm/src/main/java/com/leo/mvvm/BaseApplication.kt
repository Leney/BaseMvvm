package com.leo.mvvm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application 基类
 */
abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        APP_CONTEXT = applicationContext
        // koin注入初始化
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
        }
    }
}