package com.acabes.carapp.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acabes.carapp.R
import com.acabes.carapp.model.Vehicles

class VehicleTypeAdapter(private val items: List<Vehicles>) : RecyclerView.Adapter<VehicleTypeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val vehicleTypeTextView=itemView.findViewById<TextView>(R.id.vehicle_item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleTypeAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.vehicle_type_list_item,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items[position]
        holder.vehicleTypeTextView.text=item.Name
    }
    override fun getItemCount(): Int =items.size
}
