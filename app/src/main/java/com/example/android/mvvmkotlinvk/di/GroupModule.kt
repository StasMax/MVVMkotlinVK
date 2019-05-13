package com.example.android.mvvmkotlinvk.di

import android.arch.lifecycle.ViewModelProvider
import com.example.android.mvvmkotlinvk.data.repository.*
import com.example.android.mvvmkotlinvk.interactor.GroupInteractorImpl
import com.example.android.mvvmkotlinvk.interactor.IGroupInteractor
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.ViewModelFactory
import toothpick.config.Module

class GroupModule : Module() {
    init {
        this.bind(IVkRepository::class.java).to(VkRepositoryImpl::class.java).singletonInScope()
        this.bind(IRealmDb::class.java).to(RealmDbImpl::class.java).singletonInScope()
        this.bind(ILocalDbRepository::class.java).to(LocalDbRepositoryImpl::class.java).singletonInScope()
        this.bind(IGroupInteractor::class.java).to(GroupInteractorImpl::class.java).singletonInScope()
        this.bind(ViewModelProvider.Factory::class.java).to(ViewModelFactory::class.java)
    }
}