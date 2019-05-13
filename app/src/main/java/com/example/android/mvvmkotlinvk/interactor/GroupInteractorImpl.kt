package com.example.android.mvvmkotlinvk.interactor

import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.example.android.mvvmkotlinvk.data.repository.ILocalDbRepository
import com.example.android.mvvmkotlinvk.data.repository.IVkRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GroupInteractorImpl
@Inject constructor (
    private val localRepository: ILocalDbRepository,
    private val vkRepository: IVkRepository
) : IGroupInteractor {

    override fun putModelsInDb(models: List<ModelGroup>): Completable {
        return localRepository.insertAll(models)
    }

    override fun updeteModelInDb(model: ModelGroup): Completable {
        return localRepository.update(model)
    }

    override fun getFavoriteGroups(): Flowable<List<ModelGroup>> {
        return localRepository.getFavoriteFromLocalDb()
    }

    override fun getAllGroups(): Flowable<List<ModelGroup>> {
        return localRepository.getAllFromLocalDb()
    }

    override fun getAllListGroupsVk(): Single<List<ModelGroup>> {
        return vkRepository.getGroupsFromVk()
    }
}