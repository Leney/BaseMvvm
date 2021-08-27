package com.leo.base_business.base.ui

import androidx.databinding.ViewDataBinding
import com.leo.mvvm.vm.BaseVMActivity

/**
 * 业务相关 Activity 基类
 */
abstract class BusinessVMActivity<VM: BusinessViewModel,V:ViewDataBinding>:BaseVMActivity<VM,V>() {
}