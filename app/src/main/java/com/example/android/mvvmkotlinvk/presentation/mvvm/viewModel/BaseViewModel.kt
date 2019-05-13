package com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    var compositeDisposable = CompositeDisposable()

    fun disposeBag(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}