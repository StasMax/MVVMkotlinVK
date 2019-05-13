package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.example.android.mvvmkotlinvk.presentation.adapter.GroupAdapterRv
import com.example.android.mvvmkotlinvk.presentation.adapter.Listener
import com.example.android.mvvmkotlinvk.presentation.app.App
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.FavoriteViewModel
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_favorite.*
import toothpick.Toothpick
import javax.inject.Inject

class FavoriteActivity : BaseActivity() {
    lateinit var viewModel: FavoriteViewModel
    lateinit var groupAdapterRv: GroupAdapterRv
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, App.scope)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        viewModel.onInitFavoriteGroups()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel::class.java)
        initFields()
        initFavoriteRecycler()
    }

    private fun initFields() {
        viewModel.txtGroupsNoItemVis.observe(this, Observer { txtGroupsNoItemFavorite.visibility = it!! })
        viewModel.recyclerGroupsVis.observe(this, Observer { favoriteRecyclerView.visibility = it!! })
    }

    private fun initFavoriteRecycler() {
        groupAdapterRv = GroupAdapterRv()
        favoriteRecyclerView.adapter = groupAdapterRv
        favoriteListener(groupAdapterRv)
        viewModel.modelGroups.observe(this, Observer { list -> list?.let { groupAdapterRv.setupFavoriteGroups(it) } })
    }

    private fun favoriteListener(groupAdapterRv: GroupAdapterRv) {
        groupAdapterRv.listener = (object : Listener {
            override fun onClick(groupModel: ModelGroup, isChecked: Boolean) {
                viewModel.onSetFavorite(groupModel, isChecked)
            }
        })
    }
}
