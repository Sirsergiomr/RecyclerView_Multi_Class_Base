package com.example.contactosmyphp
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

abstract class BaseViewHolder<T>(itemView: View):RecyclerView.ViewHolder(itemView) {
    abstract fun bind(iten: T,position:Int)
}