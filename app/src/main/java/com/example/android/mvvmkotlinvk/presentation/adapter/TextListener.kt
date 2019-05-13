package com.example.android.mvvmkotlinvk.presentation.adapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChange(callback: (text: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            callback(s, start, before, count)
        }
    })
}