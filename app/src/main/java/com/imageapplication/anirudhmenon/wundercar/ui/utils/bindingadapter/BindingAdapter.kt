package com.imageapplication.anirudhmenon.wundercar.ui.utils.bindingadapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

@BindingAdapter("data")
fun <T> setRecyclerViewProps(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}