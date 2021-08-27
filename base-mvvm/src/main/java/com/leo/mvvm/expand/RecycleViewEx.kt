package com.leo.mvvm.expand

import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leo.mvvm.decoration.BlankItemDecoration
import com.leo.mvvm.decoration.GridSpaceItemDecoration
import com.leo.mvvm.decoration.LinearItemDecoration
import com.leo.mvvm.utils.dip

/**
 * desc   :
 * date   : 2020/08/04
 * version: 1.0
 */
@BindingAdapter("SingleVerticalLayout")
fun RecyclerView.singleVerticalLayout(adapter: RecyclerView.Adapter<*>?) {
    if (adapter == null) return
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = LinearLayoutManager.VERTICAL
    setLayoutManager(layoutManager)

    itemAnimator = DefaultItemAnimator()
    this.adapter = adapter
    overScrollMode = View.OVER_SCROLL_NEVER
}

@BindingAdapter("bindVerticalLayout", "bindCanScroll", requireAll = false)
fun RecyclerView.bindVerticalLayout(adapter: RecyclerView.Adapter<*>?, isCanScroll: Boolean?) {
    if (adapter == null) return
    val layoutManager = if (isCanScroll != null) {
        object : LinearLayoutManager(context) {
            override fun canScrollHorizontally(): Boolean {
                return isCanScroll
            }

            override fun canScrollVertically(): Boolean {
                return isCanScroll
            }
        }
    } else {
        LinearLayoutManager(context)
    }
    layoutManager.orientation = LinearLayoutManager.VERTICAL
    setLayoutManager(layoutManager)

    itemAnimator = DefaultItemAnimator()
    this.adapter = adapter
    overScrollMode = View.OVER_SCROLL_NEVER
}

@BindingAdapter("bindHorizontalLayout", "bindCanScroll", requireAll = false)
fun RecyclerView.bindHorizontalLayout(adapter: RecyclerView.Adapter<*>?, isCanScroll: Boolean?) {
    if (adapter == null) return
    val layoutManager = if (isCanScroll != null) {
        object : LinearLayoutManager(context) {
            override fun canScrollHorizontally(): Boolean {
                return isCanScroll
            }

            override fun canScrollVertically(): Boolean {
                return isCanScroll
            }
        }
    } else {
        LinearLayoutManager(context)
    }
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
    setLayoutManager(layoutManager)
    itemAnimator = DefaultItemAnimator()
    this.adapter = adapter
    overScrollMode = View.OVER_SCROLL_NEVER
}


@BindingAdapter("bindText")
fun RecyclerView.bindText(visible: Int) {
    visibility = visible
}

@BindingAdapter("bindGridLayout", "bindCount", "bindCanScroll", requireAll = false)
fun RecyclerView.bindGridLayout(
    adapter: RecyclerView.Adapter<*>?,
    spanCount: Int,
    isCanScroll: Boolean?
) {
    if (adapter == null) return
    val layoutManager = if (isCanScroll != null) {
        object : GridLayoutManager(context, spanCount) {
            override fun canScrollHorizontally(): Boolean {
                return isCanScroll
            }

            override fun canScrollVertically(): Boolean {
                return isCanScroll
            }
        }
    } else {
        GridLayoutManager(context, spanCount)
    }
//    val layoutManager = GridLayoutManager(context, spanCount)
    setLayoutManager(layoutManager)
    this.adapter = adapter
    overScrollMode = View.OVER_SCROLL_NEVER
}

@BindingAdapter("gridItemDecoration")
fun RecyclerView.gridItemDecoration(span: Float) {
    addItemDecoration(
        GridSpaceItemDecoration(
            dip(span),
            0,
            false,
            GridSpaceItemDecoration.GRIDLAYOUT
        )
    )
}

@BindingAdapter("linearBottomItemDecoration")
fun RecyclerView.linearBottomItemDecoration(span: Int) {
    addItemDecoration(
        LinearItemDecoration().setSpanBottom(context.dip(span))
    )
}


@BindingAdapter("linearItemDecoration")
fun RecyclerView.linearRightItemDecoration(span: Int) {
    addItemDecoration(
        LinearItemDecoration().setSpanRight(context.dip(span))
    )
}

/**
 * 水平等分(包含第一个item左边和最后一个右边等分)
 */
@BindingAdapter("linearHorItemDecoration")
fun RecyclerView.linearHorItemDecoration(span: Int) {
    addItemDecoration(
        BlankItemDecoration(
            context.dip(span),
            BlankItemDecoration.HORIZONTAL,
            context.dip(span),
            context.dip(span)
        )
    )
}

/**
 * 上下等分(包含第一个item左边和最后一个右边等分)
 */
@BindingAdapter("linearVerItemDecoration")
fun RecyclerView.linearVerItemDecoration(span: Int) {
    addItemDecoration(
        BlankItemDecoration(
            context.dip(span),
            BlankItemDecoration.VERTICAL,
            context.dip(span),
            context.dip(span)
        )
    )
}

@BindingAdapter("divLinearItemDecoration", "divHeight", "divColor")
fun RecyclerView.divLinearItemDecoration(span: Int, divHeight: Int, @ColorInt divColor: Int) {
    addItemDecoration(
        LinearItemDecoration()
            .setSpanBottom(span)
            .setDivHeight(divHeight)
            .setDivColor(divColor)
    )
}

@BindingAdapter("bindScrollTop")
fun RecyclerView.scrollToTop(isScrollToTop: Boolean) {
    if (isScrollToTop) {
        scrollToPosition(0)
    }
}

@BindingAdapter("bindScrollToPosition")
fun RecyclerView.setScrollToPosition(position: Int?) {
    if (position == null) return
    scrollToPosition(position)
}

