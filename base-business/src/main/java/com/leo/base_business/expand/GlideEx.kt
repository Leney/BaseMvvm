package com.leo.base_business.expand

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.leo.base_business.thirdlib.glide.BlurTransformation
import com.leo.base_business.thirdlib.glide.GlideBlurTransformation
import com.leo.mvvm.utils.dip

/**
 * desc   :
 * date   : 2020/08/20
 * version: 1.0
 */
//private fun glideUrl(url: String?): GlideUrl? {
//    if(url == null || url.isEmpty()) return null
//    // 图片加载添加header
//    return GlideUrl(url, LazyHeaders.Builder().addHeader("referer", BASE_URL).build())
//}

fun loadImage(imageView: ImageView, url: String?, defaultDrawable: Drawable) {
    if (imageView.context == null) return
    val options: RequestOptions = RequestOptions() //图片加载出来前，显示的图片
        .placeholder(defaultDrawable) //url为空的时候,显示的图片
        .fallback(defaultDrawable) //图片加载失败后，显示的图片
        .error(defaultDrawable)
    Glide.with(imageView).load(url)
        .apply(options)
        .into(imageView)
}

fun loadImageByTransform(
    imageView: ImageView,
    url: String?,
    defaultDrawable: Drawable,
    transformation: BitmapTransformation
) {
    if (imageView.context == null) return
    val options: RequestOptions = RequestOptions() //图片加载出来前，显示的图片
        .placeholder(defaultDrawable) //url为空的时候,显示的图片
        .fallback(defaultDrawable) //图片加载失败后，显示的图片
        .error(defaultDrawable)
        .transform(transformation)
    Glide.with(imageView).load(url)
        .apply(options)
        .into(imageView)
}

fun loadImage(imageView: ImageView, url: String?) {
    ContextCompat.getDrawable(imageView.context, android.R.drawable.sym_def_app_icon)?.let {
        loadImage(
            imageView,
            url,
            it
        )
    }
}

fun loadCircleImage(imageView: ImageView, url: String, defaultDrawable: Drawable) {
    loadImageByTransform(
        imageView,
        url,
        defaultDrawable,
        CircleCrop()
    )
}

fun loadCircleImage(imageView: ImageView, url: String?) {
    ContextCompat.getDrawable(imageView.context, android.R.drawable.sym_def_app_icon)?.let {
        loadImageByTransform(
            imageView,
            url,
            it,
            CircleCrop()
        )
    }
}

fun loadRoundCornersImage(
    corner: Float,
    imageView: ImageView,
    url: String?,
    defaultDrawable: Drawable
) {
    loadImageByTransform(
        imageView,
        url,
        defaultDrawable,
        RoundedCorners(imageView.dip(corner))
    )
}

fun loadRoundCornersImage(corner: Float, imageView: ImageView, url: String?) {
    if (corner <= 0) {
        loadImage(imageView, url)
        return
    }
    ContextCompat.getDrawable(imageView.context, android.R.drawable.sym_def_app_icon)?.let {
        loadImageByTransform(
            imageView,
            url,
            it,
            RoundedCorners(imageView.dip(corner))
        )
    }
}

fun loadBlurImage(imageView: ImageView, url: String) {
    imageView.alpha = 0.2f
    ContextCompat.getDrawable(imageView.context, android.R.drawable.sym_def_app_icon)?.let {
        loadImageByTransform(
            imageView,
            url,
            it,
            BlurTransformation(imageView.context, 25, 4)
        )
    }
}

/**
 * 设置view高斯模糊背景
 */
fun loadBlurryBackground(view: View, url: String?) {
    Glide.with(view.context)
        .asBitmap()
        .load(url)
        .apply(RequestOptions.bitmapTransform(GlideBlurTransformation(view.context)))
        .into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                view.background = BitmapDrawable(resource)
            }
        })
}

