package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.FavoriteViewModel
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.ViewModelFactory
import javax.inject.Inject

class FavoriteActivity : BaseActivity() {
    lateinit var viewModel : FavoriteViewModel
    @Inject
    internal var viewModelFactory: ViewModelFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel::class.java)
    }
}
