package com.acabes.carapp.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acabes.carapp.R
import com.acabes.carapp.model.Car

class CarsAdapter(private val items: List<Car>, private val itemClickListener:OnItemClickListener) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun onItemClicked(data:Car)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val countryTextView:TextView
        val mfrCommonNameTextView:TextView
        val listItem:LinearLayout
        init {
            itemView.apply {
                countryTextView=itemView.findViewById(R.id.Country)
                mfrCommonNameTextView=itemView.findViewById(R.id.Mfr_CommonName)
                listItem=itemView.findViewById(R.id.list_item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.vehicle_details_fragment1_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items[position]
        holder.apply {
            countryTextView.text=item.Country
            mfrCommonNameTextView.text=item.Mfr_CommonName
            listItem.setOnClickListener{
                itemClickListener.onItemClicked(item)
            }
        }
    }

    override fun getItemCount(): Int =items.size
}
