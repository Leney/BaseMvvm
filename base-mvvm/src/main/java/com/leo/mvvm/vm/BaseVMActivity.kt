package com.leo.mvvm.vm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.leo.mvvm.BR
import com.leo.mvvm.R
import com.leo.mvvm.base.BaseActivity
import com.leo.mvvm.bean.BaseEmptyViewBean
import com.leo.mvvm.bean.BaseErrorViewBean
import com.leo.mvvm.bean.BaseStatus
import com.leo.mvvm.databinding.ActivityVmBaseBinding
import com.leo.mvvm.utils.dip
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * MVVM Activity基类
 */
abstract class BaseVMActivity<VM : BaseViewModel, V : ViewDataBinding> : BaseActivity() {
    private lateinit var mBaseBinding: ActivityVmBaseBinding

    private lateinit var _viewModel: VM
    val mViewModel get() = _viewModel

    // 子视图内容的DataBind对象
    private var contentBind: ViewDataBinding? = null
    private var mContentView: View? = null // 内容显示视图

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_vm_base)
        mBaseBinding.let {
            // 反射拿到当前类的第一个泛型形参(BaseViewModel class对象)
            val clazz =
                this.javaClass.kotlin.supertypes[0].arguments[0].type?.classifier as KClass<VM>
//            // 注册viewModel
//            _viewModel = ViewModelProvider(this).get(clazz.java)
            // koin viewModel 注入
            _viewModel = getViewModel(clazz = clazz)
            it.lifecycleOwner = this
            it.viewModel = _viewModel
        }

        showTitleView()
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
        _viewModel.baseStatus.observe(this, {
            Log.i("llj", "视图状态更改--------->>>${it.viewStatus}")
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
     * 显示标题视图
     */
    private fun showTitleView() {
        if (!mBaseBinding.baseTitleLayout.isInflated) {
            mBaseBinding.baseTitleLayout.viewStub?.inflate()
        }
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

    override fun isDataBindView(): Boolean = true

    override fun onDestroy() {
        super.onDestroy()
        contentBind?.unbind()
        mBaseBinding.unbind()
    }
}