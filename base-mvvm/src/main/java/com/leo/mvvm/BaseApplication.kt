package com.leo.mvvm

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import kotlin.properties.Delegates

/**
 * Application 基类
 */
abstract class BaseApplication : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        // koin注入初始化
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
        }
    }
}