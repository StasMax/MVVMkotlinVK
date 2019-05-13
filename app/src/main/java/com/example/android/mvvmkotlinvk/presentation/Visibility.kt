package com.example.android.mvvmkotlinvk.presentation

import android.view.View

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeUnvisible() {
    visibility = View.GONE
}