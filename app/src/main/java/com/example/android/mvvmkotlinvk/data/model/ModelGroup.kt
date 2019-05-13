package com.example.android.mvvmkotlinvk.data.model

import io.realm.RealmObject

open class ModelGroup(
    var name: String? = null,
    var subscribers: String? = null,
    var avatar: String? = null,
    var isFavorite: Boolean = false
) :
    RealmObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ModelGroup
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }
}