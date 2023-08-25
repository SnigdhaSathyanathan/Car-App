package com.acabes.carapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acabes.carapp.R
import com.acabes.carapp.model.Car
import com.acabes.carapp.view_model.Fragment1ViewModel
import com.acabes.carapp.view_model.CarsAdapter

class CarFragment : Fragment(), CarsAdapter.OnItemClickListener{
    lateinit var randomQuoteTextView:TextView
    lateinit var refreshButton:ImageButton
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflated= inflater.inflate(R.layout.car_details_fragment, container, false)

        with(inflated){
            randomQuoteTextView=findViewById(R.id.quote_textview)
            refreshButton=findViewById(R.id.refresh_button)
            recyclerView=findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel:Fragment1ViewModel= ViewModelProvider(this@CarFragment).get(Fragment1ViewModel::class.java)
        viewmodel.fetchQuote()
        viewmodel.fetchCarDetails()
        viewmodel.quoteItem.observe(viewLifecycleOwner){result->
            randomQuoteTextView.text=result
        }
        viewmodel.carItem.observe(viewLifecycleOwner){result->
            val adapter=CarsAdapter(result,this)
            recyclerView.adapter=adapter
        }
        refreshButton.setOnClickListener{
            viewmodel.fetchQuote()
        }
    }
    override fun onItemClicked(data: Car) {
        val bundle=Bundle().apply {
            putParcelable(carDetailsKey,data)
        }

        val fragment2 = VehicleTypeFragment.newInstance()
        fragment2.arguments=bundle
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragment2, "fragment2")
            .commit()
    }

    companion object {
        const val carDetailsKey="car"
        @JvmStatic
        fun newInstance() = CarFragment()
    }
}