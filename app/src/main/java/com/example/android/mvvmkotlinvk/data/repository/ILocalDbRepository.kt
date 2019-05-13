package com.example.android.mvvmkotlinvk.data.repository

import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable

interface ILocalDbRepository {
    fun insertAll(vkModels: List<ModelGroup>): Completable

    fun update(model: ModelGroup): Completable

    fun getAllFromLocalDb(): Flowable<List<ModelGroup>>

    fun getFavoriteFromLocalDb(): Flowable<List<ModelGroup>>
}