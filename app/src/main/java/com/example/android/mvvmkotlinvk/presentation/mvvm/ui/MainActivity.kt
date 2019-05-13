package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.MainViewModel
import com.example.android.mvvmkotlinvk.presentation.mvvm.viewModel.ViewModelFactory
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import javax.inject.Inject

class MainActivity : BaseActivity() {
    var vkLoad = true
    lateinit var viewModel : MainViewModel
    @Inject
    internal var viewModelFactory: ViewModelFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { vkLoad = savedInstanceState.getBoolean("vkLoad") }
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        if (isNetworkConnected() && vkLoad) {
            VKSdk.login(this, VKScope.GROUPS)
            vkLoad = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                onInitGroupsVk()
            }

            override fun onError(error: VKError) {
                showMessage(R.string.error_login)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.let { outState.putBoolean("vkLoad", vkLoad) }
    }
}
