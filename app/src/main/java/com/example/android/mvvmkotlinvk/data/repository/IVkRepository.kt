package com.example.android.mvvmkotlinvk.data.repository

import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import io.reactivex.Single

interface IVkRepository {
    fun getGroupsFromVk(): Single<List<ModelGroup>>
}