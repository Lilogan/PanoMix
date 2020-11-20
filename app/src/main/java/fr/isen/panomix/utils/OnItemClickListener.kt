package fr.isen.panomix.utils

import android.view.View

interface OnItemClickListener<T> {
    abstract fun onItemClick(item: T, position: Int)
}