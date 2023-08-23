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
import com.acabes.carapp.view_model.MyAdapter2

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentTwo : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var Country:TextView
    lateinit var CommonName:TextView
    lateinit var ID:TextView
    lateinit var Name:TextView
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflated= inflater.inflate(R.layout.fragment_two, container, false)
        with(inflated){
            Country=findViewById(R.id.country_value)
            CommonName=findViewById(R.id.commonName_value)
            ID=findViewById(R.id.id_value)
            Name=findViewById(R.id.name_value)
            recyclerView=findViewById(R.id.recyclerView2)
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receivedData=arguments?.getParcelable<Car>("car")
        Country.text=receivedData?.Country
        CommonName.text=receivedData?.Mfr_CommonName
        ID.text=receivedData?.Mfr_ID.toString()
        Name.text=receivedData?.Mfr_Name
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        var adapter= receivedData?.VehicleTypes?.let { MyAdapter2(it) }
        recyclerView.adapter=adapter
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}