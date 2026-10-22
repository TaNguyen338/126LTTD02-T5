package com.example.b18.utils

import android.widget.EditText
import androidx.core.widget.doOnTextChanged

fun EditText.trimmedText(): String = text.toString().trim()

fun EditText.onTextChange(action: (String) -> Unit) {
    doOnTextChanged { text, _, _, _ -> action(text.toString().trim()) }
}