package com.leo.base_business.expand

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(
    "corner",
    "bindUrl",
    "isCircle",
    "defaultIcon",
    "errorIcon",
    "isShowErrorIcon",
    requireAll = false
)
fun ImageView.bindUrl(
    corner: Float = 0f,
    bindUrl: String?,
    isCircle: Boolean = false,
    defaultIcon: Drawable? = null,
    errorIcon: Drawable? = null,
    isShowErrorIcon: Boolean = false
) {
    if (isShowErrorIcon) {
        if (errorIcon != null) {
            setImageDrawable(errorIcon)
        }
        return
    }
    val url = bindUrl ?: ""
    when {
        isCircle -> {
            if (defaultIcon != null) {
                loadCircleImage(this, url, defaultIcon)
            } else {
                loadCircleImage(this, url)
            }
        }
        corner > 0 -> {
            if (defaultIcon != null) {
                loadRoundCornersImage(corner, this, url, defaultIcon)
            } else {
                loadRoundCornersImage(corner, this, url)
            }
        }
        else -> {
            if (defaultIcon != null) {
                loadImage(this, url, defaultIcon)
            } else {
                loadImage(this, url)
            }
        }
    }
}

@BindingAdapter("blurUrl")
fun ImageView.blurUrl(blurUrl: String?) {
    val url = blurUrl ?: ""
    loadBlurImage(this, url)
}