package com.acabes.carapp.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acabes.carapp.R
import com.acabes.carapp.model.Vehicles

class MyAdapter2(private val items: List<Vehicles>) : RecyclerView.Adapter<MyAdapter2.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val vehicle_type=itemView.findViewById<TextView>(R.id.vehicle_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter2.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.details_list_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items[position]
        holder.vehicle_type.text=item.Name
    }

    override fun getItemCount(): Int =items.size
}
