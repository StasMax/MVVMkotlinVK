package com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel

import android.arch.lifecycle.MutableLiveData
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.example.android.mvvmkotlinvk.interactor.IGroupInteractor
import com.example.android.mvvmkotlinvk.presentation.app.App
import com.example.android.mvvmkotlinvk.presentation.asyncCompletable
import com.example.android.mvvmkotlinvk.presentation.asyncFlowable
import com.example.android.mvvmkotlinvk.presentation.makeUnvisible
import com.example.android.mvvmkotlinvk.presentation.makeVisible
import toothpick.Toothpick
import javax.inject.Inject

class FavoriteViewModel : BaseViewModel() {
    init {
        Toothpick.inject(this, App.scope)
    }
    @Inject
    lateinit var interactor: IGroupInteractor
    val txtGroupsNoItemVis = MutableLiveData<Int>()
    val recyclerGroupsVis = MutableLiveData<Int>()
    val modelGroups = MutableLiveData<List<ModelGroup>>()

    fun onInitFavoriteGroups() {
        interactor
            .getFavoriteGroups()
            .asyncFlowable()
            .subscribe { onInitGroupsRecycle(it) }
            .let { disposeBag(it) }
    }

    private fun onInitGroupsRecycle(groupModelList: List<ModelGroup>) {
        if (groupModelList.isEmpty()) {
            setupEmptyList()
        } else {
            setupGroupsList(groupModelList)
        }
    }

    fun onSetFavorite(groupModel: ModelGroup, isChecked: Boolean) {
        groupModel.isFavorite = isChecked
        interactor
            .updeteModelInDb(groupModel)
            .asyncCompletable()
            .subscribe()
            .let { disposeBag(it) }
    }

    fun setupEmptyList() {
        txtGroupsNoItemVis.value = makeVisible()
        recyclerGroupsVis.value = makeUnvisible()
    }

    fun setupGroupsList(groupModelFavoriteList: List<ModelGroup>) {
        txtGroupsNoItemVis.value = makeUnvisible()
        recyclerGroupsVis.value = makeVisible()
        modelGroups.value = groupModelFavoriteList
    }
}