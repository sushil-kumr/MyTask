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

    private var list: List<SliderItem> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(list: List<SliderItem>) {
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

        fun bind(imageModel: SliderItem) {

            var imageView: ImageView = itemView.findViewById(R.id.image)

            Glide.with(itemView.context)
                .load(imageModel.imageUrl)
                .into(imageView)
        }
    }
}