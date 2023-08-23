package com.acabes.carapp.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acabes.carapp.R
import com.acabes.carapp.model.Car

class MyAdapter1(private val items: List<Car>, var itemClickListener:onItemClickListener) : RecyclerView.Adapter<MyAdapter1.ViewHolder>() {

    interface onItemClickListener{
        fun onItemClicked(data:Car)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val country=itemView.findViewById<TextView>(R.id.Country)
        val mfr_commonName=itemView.findViewById<TextView>(R.id.Mfr_CommonName)
        val listItem=itemView.findViewById<LinearLayout>(R.id.list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter1.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items[position]
        holder.country.text=item.Country
        holder.mfr_commonName.text=item.Mfr_CommonName
        holder.listItem.setOnClickListener{
            itemClickListener.onItemClicked(item)
        }

    }

    override fun getItemCount(): Int =items.size
}
