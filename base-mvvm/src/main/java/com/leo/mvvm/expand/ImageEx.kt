package com.leo.mvvm.expand

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * desc   :
 * date   : 2020/08/04
 * version: 1.0
 */
@BindingAdapter("bindSrc")
fun ImageView.bindSrc(resourceId: Int?) {
    if (resourceId != null && resourceId > 0) {
        visibility = View.VISIBLE
        setImageResource(resourceId)
    } else {
        visibility = View.GONE
    }
}

@BindingAdapter("bindSrc")
fun ImageView.bindSrc(resourceId: Int) {
    if (resourceId > 0) {
        visibility = View.VISIBLE
        setImageResource(resourceId)
    } else {
        visibility = View.GONE
    }
}

@BindingAdapter("bindVisible")
fun ImageView.bindVisible(content: String? = "") {
    val text = content ?: ""
    visibility = if (TextUtils.isEmpty(text)) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("bindOneSrc", "bindTwoSrc", "bindStatus")
fun ImageView.changeSrc(oneResource: Drawable?, twoResource: Drawable?, status: Boolean?) {
    if (oneResource == null || twoResource == null || status == null) return
    if (status) {
        setImageDrawable(oneResource)
    } else {
        setImageDrawable(twoResource)
    }
}
