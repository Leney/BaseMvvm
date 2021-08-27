package com.leo.base_business.base.ui

import androidx.databinding.ViewDataBinding
import com.leo.mvvm.vm.BaseVmFragment

/**
 * 业务相关 Fragment 基类
 */
abstract class BusinessVMFragment<VM: BusinessViewModel,V:ViewDataBinding>:BaseVmFragment<VM,V>() {
}