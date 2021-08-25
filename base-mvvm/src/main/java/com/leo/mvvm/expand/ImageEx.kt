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

//@BindingAdapter(
//    "corner",
//    "bindUrl",
//    "isCircle",
//    "defaultIcon",
//    "errorIcon",
//    "isShowErrorIcon",
//    requireAll = false
//)
//fun ImageView.bindUrl(
//    corner: Float = 0f,
//    bindUrl: String?,
//    isCircle: Boolean = false,
//    defaultIcon: Drawable? = null,
//    errorIcon: Drawable? = null,
//    isShowErrorIcon: Boolean = false
//) {
//    if (isShowErrorIcon) {
//        if (errorIcon != null) {
//            setImageDrawable(errorIcon)
//        }
//        return
//    }
//    val url = bindUrl ?: ""
//    when {
//        isCircle -> {
//            if (defaultIcon != null) {
//                loadCircleImage(this, url, defaultIcon)
//            } else {
//                loadCircleImage(this, url)
//            }
//        }
//        corner > 0 -> {
//            if (defaultIcon != null) {
//                loadRoundCornersImage(corner, this, url, defaultIcon)
//            } else {
//                loadRoundCornersImage(corner, this, url)
//            }
//        }
//        else -> {
//            if (defaultIcon != null) {
//                loadImage(this, url, defaultIcon)
//            } else {
//                loadImage(this, url)
//            }
//        }
//    }
//}
//
//@BindingAdapter("blurUrl")
//fun ImageView.blurUrl(blurUrl: String?) {
//    val url = blurUrl ?: ""
//    loadBlurImage(this, url)
//}