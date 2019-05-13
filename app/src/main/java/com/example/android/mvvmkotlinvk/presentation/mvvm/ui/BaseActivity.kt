package com.example.android.mvvmkotlinvk.presentation.mvvm.ui

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class BaseActivity: AppCompatActivity(){
    fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        return cm.activeNetworkInfo != null
    }

    fun showMessage(resource: Int){
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show()
    }
}