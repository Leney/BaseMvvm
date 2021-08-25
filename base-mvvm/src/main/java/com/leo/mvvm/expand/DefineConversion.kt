package com.leo.mvvm.expand

import android.graphics.Color
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingConversion
import com.leo.mvvm.BaseApplication.Companion.CONTEXT

/**
 * 将字符串转换成颜色Color
 */
@BindingConversion
fun convertColorToDrawable(color: String) = Color.parseColor(color)

@BindingConversion
fun convertIntToDrawable(resId: Int) = AppCompatResources.getDrawable(CONTEXT, resId)