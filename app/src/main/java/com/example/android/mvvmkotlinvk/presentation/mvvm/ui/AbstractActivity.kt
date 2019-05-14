package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class AbstractActivity<T : BaseViewModel> : BaseActivity() {
    protected val viewModel by lazy { ViewModelProviders.of(this).get(getGenericsClass<T>()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewModelReady()
    }

    protected open fun onViewModelReady() = Unit

    protected inline fun <T> LiveData<T>.observe(crossinline action: (T) -> Unit) =
        observe(this@AbstractActivity, Observer { if (it != null) action(it) })

    @Suppress("UNCHECKED_CAST")
    fun <T> Any.getGenericsClass() =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<T>
}
