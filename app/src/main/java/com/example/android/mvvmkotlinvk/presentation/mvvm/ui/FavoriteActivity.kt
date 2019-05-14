package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.example.android.mvvmkotlinvk.presentation.adapter.GroupAdapterRv
import com.example.android.mvvmkotlinvk.presentation.adapter.Listener
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AbstractActivity<FavoriteViewModel>() {

    lateinit var groupAdapterRv: GroupAdapterRv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        viewModel.onInitFavoriteGroups()
        initFavoriteRecycler()
    }

    override fun onViewModelReady() {
        viewModel.txtGroupsNoItemVis.observe(this, Observer { txtGroupsNoItemFavorite.visibility = it!! })
        viewModel.recyclerGroupsVis.observe(this, Observer { favoriteRecyclerView.visibility = it!! })
        viewModel.modelGroups.observe(this, Observer { list -> list?.let { groupAdapterRv.setupFavoriteGroups(it) } })
    }

    private fun initFavoriteRecycler() {
        groupAdapterRv = GroupAdapterRv()
        favoriteRecyclerView.adapter = groupAdapterRv
        favoriteListener(groupAdapterRv)
    }

    private fun favoriteListener(groupAdapterRv: GroupAdapterRv) {
        groupAdapterRv.listener = (object : Listener {
            override fun onClick(groupModel: ModelGroup, isChecked: Boolean) {
                viewModel.onSetFavorite(groupModel, isChecked)
            }
        })
    }
}
