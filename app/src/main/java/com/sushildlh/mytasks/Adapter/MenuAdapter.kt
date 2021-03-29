package com.sushildlh.mytasks.Adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sushildlh.mytasks.Fragments.MenuFragment
import com.sushildlh.mytasks.HomeActivity
import com.sushildlh.mytasks.Modal.MenuData
import com.sushildlh.mytasks.R


class MenuAdapter(private var menuList: List<MenuData>?, private var context: Context) :
    RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = menuList?.get(position)
        holder.image.clipToOutline = true
        Glide.with(holder.itemView.context)
            .load(menu?.image)
            .into(holder.image)
        holder.name.text = menu?.name
        holder.desc.text = menu?.desc
        holder.size.text = menu?.size
        holder.addButton.text = "USD " + menu?.price.toString()
        holder.addButton.setOnClickListener(View.OnClickListener { view ->
            holder.addButton.text = "Added + 1"
            holder.addButton.background =
                holder.itemView.context.getDrawable(R.drawable.selected_rounded)
            val activity = context as HomeActivity
            activity.updateCartItem()
            Handler(Looper.getMainLooper()).postDelayed({
                holder.addButton.text = "USD " + menu?.price.toString()
                holder.addButton.background =
                    holder.itemView.context.getDrawable(R.drawable.rounded)
            }, 500)
        })
    }

    override fun getItemCount(): Int {
        return menuList!!.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var desc: TextView = view.findViewById(R.id.desc)
        var size: TextView = view.findViewById(R.id.size)
        var image: ImageView = view.findViewById(R.id.image_layout)
        var addButton: Button = view.findViewById(R.id.add)

    }
}