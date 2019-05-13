package com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.android.mvvmkotlinvk.interactor.IGroupInteractor
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory
@Inject
constructor(private val interactor: IGroupInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(interactor) as T
        else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) return FavoriteViewModel(interactor) as T
        throw IllegalArgumentException("Unknown class name")
    }
}