package com.tooploox.songapp.bindingAdapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    /**
     * Set visibility of the view by Boolean value.
     */
    @JvmStatic
    @BindingAdapter("visible")
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    /**
     * Set visibility of the view by Boolean value.
     */
    @JvmStatic
    @BindingAdapter("numberAsText")
    fun setNumberAsText(view: TextView, number: Number) {
        view.text = number.toString()
    }

}