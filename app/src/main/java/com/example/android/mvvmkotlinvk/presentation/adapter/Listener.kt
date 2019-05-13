package com.example.android.mvvmkotlinvk.presentation.adapter

import com.example.android.mvvmkotlinvk.data.model.ModelGroup

interface Listener {
    fun onClick(groupModel: ModelGroup, isChecked: Boolean)
}