package com.example.android.mvvmkotlinvk.data.repository

import com.example.android.mvvmkotlinvk.data.decorator.EmptyQueryDecorator
import com.example.android.mvvmkotlinvk.data.decorator.getQueryDecoratorByFavorite
import com.example.android.mvvmkotlinvk.data.decorator.getQueryDecoratorByName
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import io.reactivex.Completable
import io.reactivex.Completable.fromAction
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDbRepositoryImpl
@Inject constructor(
    private val realmDb: IRealmDb
) : ILocalDbRepository {

    private var listDb = arrayListOf<ModelGroup>()
    private var listVk = arrayListOf<ModelGroup>()

    override fun insertAll(vkModels: List<ModelGroup>): Completable {
        return fromAction {
            listVk.clear()
            listDb.clear()
            listVk.addAll(vkModels)
            listDb.addAll(realmDb.getItemList(EmptyQueryDecorator(), ModelGroup::class.java))
            listVk.filter { !listDb.contains(it) }.forEach { realmDb.saveItem(item = it) }
            listDb.filter { !listVk.contains(it) }.forEach {
                realmDb.delItem(getQueryDecoratorByName(it), ModelGroup::class.java)
            }
        }
    }

    override fun update(model: ModelGroup): Completable {
        return fromAction {
            realmDb.updateFavorite(getQueryDecoratorByName(model), model)
        }
    }

    override fun getAllFromLocalDb(): Flowable<List<ModelGroup>> {
        return realmDb.getItems(ModelGroup::class.java)
    }

    override fun getFavoriteFromLocalDb(): Flowable<List<ModelGroup>> {
        return realmDb.getItems(getQueryDecoratorByFavorite(), ModelGroup::class.java)
    }
}