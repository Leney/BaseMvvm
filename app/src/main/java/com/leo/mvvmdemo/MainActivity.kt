package com.leo.mvvmdemo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.leo.base_business.permissions.requestPermissionsForResult
import com.leo.mvvm.utils.DLog
import com.leo.mvvm.utils.app.installApp
import com.leo.mvvmdemo.activity.ActivityRecommendVideoList
import com.leo.mvvmdemo.test.TestActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.testBtn).setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        findViewById<Button>(R.id.recommendVideoListBtn).setOnClickListener {
            startActivity(Intent(this, ActivityRecommendVideoList::class.java))
        }


        findViewById<Button>(R.id.permissionsRequestBtn).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = requestPermissionsForResult(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                DLog.i("llj", "result----->>>$result")
                if (result) {
                    DLog.i("llj", "同意了权限！！")
                }
            }
        }

        findViewById<Button>(R.id.installBtn).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = requestPermissionsForResult(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                DLog.i("llj", "result----->>>$result")
                if (result) {
                    DLog.i("llj", "同意了权限！！")
                    val filePath = "${getSDPath()}/apk/qqread.apk"

                    CoroutineScope(Dispatchers.Main).launch {
                        installApp(filePath)
                    }
                }
            }
        }

    }

    private fun getSDPath(): String {
        var sdDir: File? = null
        val sdCardExist = (Environment.getExternalStorageState()
                == Environment.MEDIA_MOUNTED) //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory() //获取跟目录
        }
        return sdDir.toString()
    }
}