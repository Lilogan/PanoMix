package fr.isen.panomix.`interface`

import android.view.View

interface OnItemClickListener {
    abstract fun onItemClick(position: Int, view: View?)
}