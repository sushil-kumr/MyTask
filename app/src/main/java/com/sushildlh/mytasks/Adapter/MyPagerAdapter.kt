package com.sushildlh.mytasks.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sushildlh.mytasks.Modal.SliderItem
import com.sushildlh.mytasks.R

class MyPagerAdapter : RecyclerView.Adapter<MyPagerAdapter.MyViewHolder>() {

    private var list: List<String> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setItem(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class MyViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pager_item,
                parent, false
            )
        )

        fun bind(imageModel: String) {

            var imageView: ImageView = itemView.findViewById(R.id.image)

            Glide.with(itemView.context)
                .load(imageModel)
                .into(imageView)
        }
    }
}