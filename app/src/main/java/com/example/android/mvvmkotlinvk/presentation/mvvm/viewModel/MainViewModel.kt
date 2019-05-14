package com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel

import android.arch.lifecycle.MutableLiveData
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.example.android.mvvmkotlinvk.interactor.IGroupInteractor
import com.example.android.mvvmkotlinvk.presentation.*
import com.example.android.mvvmkotlinvk.presentation.app.App
import toothpick.Toothpick
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    init {
        Toothpick.inject(this, App.scope)
    }

    @Inject
    lateinit var interactor: IGroupInteractor
    val showToast = MutableLiveData<Int>()
    val txtGroupsNoItemVis = MutableLiveData<Int>()
    val recyclerGroupsVis = MutableLiveData<Int>()
    val cpvGroupsVis = MutableLiveData<Int>()
    val modelGroups = MutableLiveData<List<ModelGroup>>()

    fun onInitGroupsVk() {
        interactor
            .getAllListGroupsVk()
            .asyncSingle()
            .doOnSubscribe { startLoading() }
            .flatMapCompletable { interactor.putModelsInDb(it) }
            .subscribe()
            .let { disposeBag(it) }
    }

    private fun startLoading() {
        txtGroupsNoItemVis.value = makeUnvisible()
        recyclerGroupsVis.value = makeUnvisible()
        cpvGroupsVis.value = makeVisible()
    }

    fun onInitGroupsDb() {
        interactor
            .getAllGroups()
            .asyncFlowable()
            .subscribe {
                onInitGroupsRecycle(it)
                cpvGroupsVis.value = makeUnvisible()
            }
            .let { disposeBag(it) }
    }

    private fun onInitGroupsRecycle(groupModelList: List<ModelGroup>) {
        if (groupModelList.isEmpty()) {
            setupEmptyList()
            showToast.value = R.string.no_groups_item
        } else {
            setupGroupsList(groupsList = groupModelList)
        }
    }

    private fun setupGroupsList(groupsList: List<ModelGroup>) {
        txtGroupsNoItemVis.value = makeUnvisible()
        recyclerGroupsVis.value = makeVisible()
        modelGroups.value = groupsList
    }

    private fun setupEmptyList() {
        txtGroupsNoItemVis.value = makeVisible()
        recyclerGroupsVis.value = makeUnvisible()
    }

    fun onSetFavorite(groupModel: ModelGroup, isChecked: Boolean) {
        groupModel.isFavorite = isChecked
        interactor
            .updeteModelInDb(groupModel)
            .asyncCompletable()
            .subscribe()
            .let { disposeBag(it) }
    }
}