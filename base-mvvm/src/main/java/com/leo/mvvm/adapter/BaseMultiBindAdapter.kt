package com.leo.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.leo.mvvm.vm.BaseViewModel


/**
 *  dataBinding模式抽象的多Item基类BaseAdapter
 * @param itemViewList 对应的item视图的布局文件id(同时也是itemType，用的同一个值)
 * @param dataList 数据源列表
 * @param itemBeanBrId 单条item对应的Bean brId(这里强制每个item布局对应的item Bean 的brId名称都一样，否则无效)
 * @param baseViewModelBrId 如果adapter中，需要对应界面的viewModel，则传入界面的viewModel对应的brId
 * @param baseViewModel 如果adapter中，需要对应界面的viewModel，则传入界面的viewModel对象
 */
class BaseMultiBindAdapter<T : MultiItemEntity> constructor(
    private val dataList: MutableList<T>,
    private val itemBeanBrId: Int,
    private val itemViewList: List<Int>,
    private val baseViewModelBrId: Int? = null,
    private val baseViewModel: BaseViewModel? = null,
) : BaseMultiItemQuickAdapter<T, BaseViewHolder>() {

    init {
        // 循环添加对应的itemType
        itemViewList.forEach {
            addItemType(it, it)
        }
        data = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            HEADER_VIEW, EMPTY_VIEW, FOOTER_VIEW, LOAD_MORE_VIEW -> {
                super.onCreateViewHolder(parent, viewType)
            }
            else -> {
                val mDataBinding: ViewDataBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false)
                val baseBindHolder = BaseBindHolder(mDataBinding)
                bindViewClickListener(baseBindHolder, viewType)
                return baseBindHolder
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
                DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                    ?.setVariable(itemBeanBrId, data[position - headerLayoutCount])
                convert(holder, data[position - headerLayoutCount])
                DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                    ?.executePendingBindings()
            }
        }
    }

    override fun convert(holder: BaseViewHolder, item: T) {
    }

    inner class BaseBindHolder constructor(binding: ViewDataBinding) : BaseViewHolder(binding.root)
}
