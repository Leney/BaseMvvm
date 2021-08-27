package com.leo.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.leo.mvvm.vm.BaseViewModel


/**
 *
 * dataBinding模式抽象的单Item基类BaseAdapter
 *
 * @param layoutId item view的布局文件
 * @param itemDataBrId 单条item对应的Bean brId
 * @param dataList 对应的数据集合
 * @param baseViewModelBrId 如果adapter中，需要对应界面的viewModel，则传入界面的viewModel对应的brId
 * @param baseViewModel 如果adapter中，需要对应界面的viewModel，则传入界面的viewModel对象
 */
class BaseBindAdapter<T> constructor(
    private val layoutId: Int,
    private val itemDataBrId: Int,
    dataList: MutableList<T>,
    private val baseViewModelBrId: Int? = null,
    private val baseViewModel: BaseViewModel? = null,
) :
    BaseQuickAdapter<T, BaseViewHolder>(layoutId, dataList) {

    private var mDataBinding: ViewDataBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEADER_VIEW, EMPTY_VIEW, FOOTER_VIEW, LOAD_MORE_VIEW -> {
                super.onCreateViewHolder(parent, viewType)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                mDataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
                val baseViewHolder = BaseViewHolder(mDataBinding!!.root)
                bindViewClickListener(baseViewHolder, viewType)
                return baseViewHolder
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when (getItemViewType(position)) {
            HEADER_VIEW, EMPTY_VIEW, FOOTER_VIEW, LOAD_MORE_VIEW -> {
                super.onBindViewHolder(holder, position)
            }
            else -> {
                // 判断绑定对应的BaseViewModel对象
                if (baseViewModelBrId != null && baseViewModel != null) {
                    DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                        ?.setVariable(baseViewModelBrId, baseViewModel)
                }
                // 绑定单个条目的数据
                DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                    ?.setVariable(itemDataBrId, data[position - headerLayoutCount])
                convert(holder, data[position - headerLayoutCount])
                DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                    ?.executePendingBindings()
            }
        }
    }

    override fun convert(holder: BaseViewHolder, item: T) {
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mDataBinding?.unbind()
        mDataBinding = null
    }
}