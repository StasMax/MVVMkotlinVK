package com.example.android.mvvmkotlinvk.interactor

import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IGroupInteractor {
    fun getAllGroups(): Flowable<List<ModelGroup>>

    fun getFavoriteGroups(): Flowable<List<ModelGroup>>

    fun putModelsInDb(models: List<ModelGroup>): Completable

    fun updeteModelInDb(model: ModelGroup): Completable

    fun getAllListGroupsVk(): Single<List<ModelGroup>>
}