package com.example.android.mvvmkotlinvk.data.decorator


import io.realm.RealmModel
import io.realm.RealmQuery

interface QueryDecorator {
    fun <T : RealmModel> decorateQuery(query: RealmQuery<T>): RealmQuery<T>
}