package fr.isen.panomix.utils

import android.view.View

interface OnItemClickListener {
    abstract fun onItemClick(position: Int, view: View?)
}