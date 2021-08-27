package com.leo.mvvm.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BlankItemDecoration(
    private val spaceHeight: Int,
    private val orientation: Int = VERTICAL,
    private val firstItemPaddingStart: Int = 0,
    private val lastItemPaddingEnd: Int = 0
) : RecyclerView.ItemDecoration() {

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }

    init {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw IllegalArgumentException("invalid orientation:$orientation")
        }
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.adapter != null) {
            val totalItemCount = parent.adapter!!.itemCount
            val itemPosition = parent.getChildAdapterPosition(view)
            if (itemPosition != totalItemCount - 1 || totalItemCount == 1) {
                //For all the items except the last one
                // 增加了一个 "|| totalItemCount == 1"的判断，因为只有1条数据的时候，会显示贴边
                with(spaceHeight) {
                    when (orientation) {
                        HORIZONTAL -> outRect.right = this
                        VERTICAL -> outRect.bottom = this
                    }
                }
                //This is the first item
                if (itemPosition == 0) {
                    if (firstItemPaddingStart != 0) {
                        with(firstItemPaddingStart) {
                            when (orientation) {
                                HORIZONTAL -> outRect.left = this
                                VERTICAL -> outRect.top = this
                            }
                        }
                    }
                }
            } else {
                //This is the last item
                if (lastItemPaddingEnd != 0) {
                    with(lastItemPaddingEnd) {
                        when (orientation) {
                            HORIZONTAL -> outRect.right = this
                            VERTICAL -> outRect.bottom = this
                        }
                    }
                }
            }
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }

}