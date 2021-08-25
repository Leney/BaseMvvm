package com.leo.mvvm.vm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.leo.mvvm.BR
import com.leo.mvvm.R
import com.leo.mvvm.base.BaseFragment
import com.leo.mvvm.bean.BaseEmptyViewBean
import com.leo.mvvm.bean.BaseErrorViewBean
import com.leo.mvvm.bean.BaseStatus
import com.leo.mvvm.databinding.FragmentVmBaseBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/**
 * MVVM Fragment基类
 */
abstract class BaseVmFragment<VM : BaseViewModel, V : ViewDataBinding> : BaseFragment() {

    private lateinit var mBaseBinding: FragmentVmBaseBinding

    private lateinit var _viewModel: VM
    val mViewModel get() = _viewModel

    // 子视图内容的DataBind对象
    private var contentBind: ViewDataBinding? = null
    private var mContentView: View? = null // 内容显示视图

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBaseBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_vm_base, container, false)
        mBaseBinding.let {
            it.lifecycleOwner = this
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clazz = this.javaClass.kotlin.supertypes[0].arguments[0].type?.classifier as KClass<VM>
        _viewModel = getViewModel(clazz = clazz) //koin 注入
        mBaseBinding.viewModel = _viewModel
        // fragment 默认不显示标题
        _viewModel.baseTitleModel.isTitleVisible.set(false)
        // 数据变化监听
        observeData()
        initView()
        initData()
    }

    /**
     * 监听数据变化
     */
    protected open fun observeData() {
        // 监听视图显示状态变化
        _viewModel.baseStatus.observe(viewLifecycleOwner, {
            when (it.viewStatus) {
                BaseStatus.BASE_STATUS_CONTENT -> showContentView() // 显示内容视图
                BaseStatus.BASE_STATUS_LOADING -> showLoadingView() // 显示正在加载视图
                BaseStatus.BASE_STATUS_ERROR -> showErrorView() // 显示错误视图
                BaseStatus.BASE_STATUS_EMPTY -> showEmptyView() // 显示空视图
                BaseStatus.BASE_STATUS_TOAST -> showToastView() // 显示Toast视图
            }
        })
    }

    /**
     * 显示内容视图
     */
    private fun showContentView() {
        if (!mBaseBinding.baseChildView.isInflated) {
            // 没有显示过,填充显示
            mContentView = mBaseBinding.baseChildView.viewStub?.apply {
                layoutResource = getLayoutId()
                inflate().apply {
                    // 子视图布局文件，viewModel的name属性一定要是“viewModel”，这个值一定不能变，因为这里写死了，切记！！！
                    DataBindingUtil.bind<V>(this)?.setVariable(BR.viewModel, _viewModel)
                }
            }
//            mBaseBinding.baseChildView.viewStub?.layoutResource = getLayoutId()
//            mContentView = mBaseBinding.baseChildView.viewStub?.inflate()!!.apply {
//                // 子视图布局文件，viewModel的name属性一定要是“viewModel”，这个值一定不能变，因为这里写死了，切记！！！
//                DataBindingUtil.bind<V>(this)?.setVariable(BR.viewModel, _viewModel)
//            }
        }
        mContentView?.visibility = View.VISIBLE
    }

    /**
     * 显示正在加载视图
     */
    private fun showLoadingView() {
        if (!mBaseBinding.baseLoad.isInflated) {
            // 没有显示过,填充显示
            // 设置正在加载视图资源
            mBaseBinding.baseLoad.viewStub?.layoutResource = getLoadingLayout()
            // 绑定
            mBaseBinding.baseLoad.viewStub?.inflate()!!.apply {
                DataBindingUtil.bind<V>(this)?.setVariable(0, _viewModel)
            }
        }
        mContentView?.visibility = View.GONE
    }

    /**
     * 显示错误视图
     */
    private fun showErrorView() {
        if (!mBaseBinding.baseError.isInflated) {
            // 没有显示过,填充显示
            val errorBean = getErrorLayout()
            // 设置正在加载视图资源
            mBaseBinding.baseError.viewStub?.layoutResource = errorBean.layoutId
            // 绑定
            mBaseBinding.baseError.viewStub?.inflate()!!.apply {
                // BR.viewModel的名称，和错误视图里对应的viewModel name属性要对应
                DataBindingUtil.bind<V>(this)?.setVariable(errorBean.brId, _viewModel)
            }
        }
        mContentView?.visibility = View.GONE
    }

    /**
     * 显示空视图
     */
    private fun showEmptyView() {
        if (!mBaseBinding.baseEmpty.isInflated) {
            // 没有显示过,填充显示
            val emptyViewBean = getEmptyLayout()
            // 设置正在加载视图资源
            mBaseBinding.baseEmpty.viewStub?.layoutResource = emptyViewBean.layoutId
            // 绑定
            mBaseBinding.baseEmpty.viewStub?.inflate()!!.apply {
                DataBindingUtil.bind<V>(this)?.setVariable(emptyViewBean.brId, _viewModel)
            }
        }
        mContentView?.visibility = View.GONE
    }

    /**
     * 显示Toast
     */
    private fun showToastView() {

    }

    /**
     * 获取正在加载的视图(通过重载，自定义)
     */
    protected open fun getLoadingLayout(): Int = R.layout.layout_base_loading

    /**
     * 获取加载错误的视图(通过重载，自定义)
     */
    protected open fun getErrorLayout(): BaseErrorViewBean =
        BaseErrorViewBean(R.layout.layout_base_error, BR.viewModel)

    /**
     * 获取空数据的视图(通过重载，自定义)
     */
    protected open fun getEmptyLayout(): BaseEmptyViewBean =
        BaseEmptyViewBean(R.layout.layout_base_empty, BR._all)

    abstract fun initView()
    protected open fun initData() {}

    override fun isDataBindView(): Boolean = true

    override fun onDestroy() {
        super.onDestroy()
        contentBind?.unbind()
        mBaseBinding.unbind()
    }
}