package com.example.android.mvvmkotlinvk.data.repository

import com.example.android.mvvmkotlinvk.data.decorator.QueryDecorator
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import io.reactivex.Flowable
import io.realm.RealmObject

interface IRealmDb {

    fun <T : RealmObject> saveItem(item: T)

    fun <T : RealmObject> saveItems(list: List<T>?)

    fun <E : RealmObject> getItem(decorator: QueryDecorator, clazz: Class<E>): E?

    fun <E : RealmObject> delItem(clazz: Class<E>)

    fun <E : RealmObject> delItem(decorator: QueryDecorator, clazz: Class<E>)

    fun <E : RealmObject> getItems(clazz: Class<E>): Flowable<List<E>>

    fun <E : RealmObject> getItems(decorator: QueryDecorator, clazz: Class<E>): Flowable<List<E>>

    fun <E : RealmObject> getItemList(decorator: QueryDecorator, clazz: Class<E>): List<E>

    fun updateFavorite(decorator: QueryDecorator, model: ModelGroup)
}