package com.gbulan.shoestore

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

object DataBindingUtil {
    @BindingAdapter("emptyIfZero")
    @JvmStatic
    fun setText(editText: EditText, text: String?) {
        if (text == "0" || text == "0.0") editText.setText("") else editText.setText(text)
    }

    @InverseBindingAdapter(attribute = "emptyIfZero", event = "android:textAttrChanged")
    @JvmStatic
    fun getText(editText: EditText) = editText.text.toString()
}