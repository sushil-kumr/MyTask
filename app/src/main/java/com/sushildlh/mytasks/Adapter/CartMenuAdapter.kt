package com.sushildlh.mytasks.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sushildlh.mytasks.Modal.MenuData
import com.sushildlh.mytasks.R

class CartMenuAdapter(private var menuList: List<MenuData>) :
    RecyclerView.Adapter<CartMenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = menuList[position]
        holder.image.clipToOutline = true
        Glide.with(holder.itemView.context)
            .load("https://i.pinimg.com/originals/fa/56/36/fa5636e57996a29097c6ff7e5b8b9b06.jpg")
            .into(holder.image)
        holder.price.setOnClickListener(View.OnClickListener { view->

        })
    }
    override fun getItemCount(): Int {
        return menuList.size
    }
     class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var price: TextView = view.findViewById(R.id.price)
        var image: ImageView = view.findViewById(R.id.image)

    }
}