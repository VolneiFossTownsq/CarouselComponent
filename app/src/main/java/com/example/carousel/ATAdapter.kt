package com.example.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ATAdapter<V : ATViewHolder<M, B>, M, B : ViewBinding>(
    private val viewHolder: (ViewGroup) -> V,
) : RecyclerView.Adapter<V>() {

    var items: MutableList<M> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return viewHolder(parent)
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

abstract class ATViewHolder<M, B : ViewBinding>(
    bindingLaunch: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding,
    root: ViewGroup,
    val binding: B = bindingLaunch(LayoutInflater.from(root.context), root, false) as B
) : RecyclerView.ViewHolder(
    binding.root
) {

    abstract fun bind(item: M)

}