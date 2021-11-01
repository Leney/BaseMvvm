package com.leo.mvvmdemo

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.leo.base_business.permissions.requestPermissionsForResult
import com.leo.mvvm.utils.DLog
import com.leo.mvvmdemo.activity.ActivityRecommendVideoList
import com.leo.mvvmdemo.test.TestActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


    }
}