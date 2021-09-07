package com.example.movieapp2.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseConcatHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adpater: T)
}