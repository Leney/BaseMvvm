package com.leo.mvvmdemo

import com.leo.mvvmdemo.repository.HelloRepository
import com.leo.mvvmdemo.repository.HelloRepositoryImpl
import com.leo.mvvmdemo.test.TestViewModel
import com.leo.mvvmdemo.test.fragment.TestFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 需要使用koin注入的相关module，统一都在此处进行声明
 */
val appModules = module {
    single<HelloRepository> { HelloRepositoryImpl() }
    // 这个的"get()"就相当于TestViewModel实例化时声明的需要传入HelloRepository对象
    // 但传入的HelloRepository对象，也必须在koin里面声明注入了才行
    viewModel { TestViewModel(get()) }
    viewModel { TestFragmentViewModel() }
}
