package com.acabes.carapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acabes.carapp.R
import com.acabes.carapp.model.Car
import com.acabes.carapp.view_model.VehicleTypeAdapter

class VehicleTypeFragment : Fragment() {
    lateinit var countryTextView:TextView
    lateinit var commonNameTextView:TextView
    lateinit var idTextView:TextView
    lateinit var nameTextView:TextView
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflated= inflater.inflate(R.layout.vehicle_type_fragment, container, false)
        with(inflated){
            countryTextView=findViewById(R.id.country_value)
            commonNameTextView=findViewById(R.id.commonName_value)
            idTextView=findViewById(R.id.id_value)
            nameTextView=findViewById(R.id.name_value)
            recyclerView=findViewById(R.id.recyclerView2)
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receivedData=arguments?.getParcelable<Car>(carDetailsKey)
        countryTextView.text=receivedData?.Country
        commonNameTextView.text=receivedData?.Mfr_CommonName
        idTextView.text=receivedData?.Mfr_ID.toString()
        nameTextView.text=receivedData?.Mfr_Name
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter= receivedData?.VehicleTypes?.let { VehicleTypeAdapter(it) }
        recyclerView.adapter=adapter
    }
    companion object {
        const val carDetailsKey="car"
        @JvmStatic
        fun newInstance() = VehicleTypeFragment()
    }
}