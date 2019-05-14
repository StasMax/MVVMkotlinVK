package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.example.android.mvvmkotlinvk.presentation.adapter.GroupAdapterRv
import com.example.android.mvvmkotlinvk.presentation.adapter.Listener
import com.example.android.mvvmkotlinvk.presentation.adapter.onTextChange
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.MainViewModel
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity<MainViewModel>() {
    var vkLoad = true
    lateinit var adapter: GroupAdapterRv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { vkLoad = savedInstanceState.getBoolean("vkLoad") }
        setContentView(R.layout.activity_main)
        initRecycler()
        txtListener()
        clickButton()
        if (isNetworkConnected() && vkLoad) {
            VKSdk.login(this, VKScope.GROUPS)
            vkLoad = false
        }
    }

    override fun onViewModelReady() {
        viewModel.showToast.observe(this, Observer { m -> m?.let { showMessage(it) } })
        viewModel.txtGroupsNoItemVis.observe(this, Observer { txtGroupsNoItem.visibility = it!! })
        viewModel.cpvGroupsVis.observe(this, Observer { cpvGroups.visibility = it!! })
        viewModel.recyclerGroupsVis.observe(this, Observer { recyclerGroups.visibility = it!! })
        viewModel.modelGroups.observe(this, Observer { list -> list?.let { adapter.setupGroups(it) } })
    }

    private fun initRecycler() {
        adapter = GroupAdapterRv()
        recyclerGroups.adapter = adapter
        recyclerGroups.setHasFixedSize(true)
        favoriteListener(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                viewModel.onInitGroupsVk()
            }

            override fun onError(error: VKError) {
                showMessage(R.string.error_login)
            }
        })
    }

    private fun txtListener() {
        txtSearch.onTextChange { text, _, _, _ ->
            adapter.filter(text.toString())
        }
    }

    private fun clickButton() {
        floatButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.let { outState.putBoolean("vkLoad", vkLoad) }
    }

    private fun favoriteListener(groupAdapterRv: GroupAdapterRv) {
        groupAdapterRv.listener = (object : Listener {
            override fun onClick(groupModel: ModelGroup, isChecked: Boolean) {
                viewModel.onSetFavorite(groupModel, isChecked)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onInitGroupsDb()
    }
}
