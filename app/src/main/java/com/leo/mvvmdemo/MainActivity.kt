package com.leo.mvvmdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.leo.mvvmdemo.activity.ActivityRecommendVideoList
import com.leo.mvvmdemo.test.TestActivity

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
    }
}