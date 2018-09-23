package com.imageapplication.anirudhmenon.wundercar.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position: Int)
}