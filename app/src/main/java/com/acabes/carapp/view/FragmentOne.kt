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
import com.acabes.carapp.view_model.Fragment1_Activity
import com.acabes.carapp.view_model.MyAdapter1


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentOne : Fragment(), MyAdapter1.onItemClickListener{
    private var param1: String? = null
    private var param2: String? = null
    lateinit var random_quote:TextView
    lateinit var refresh_btn:ImageButton
    lateinit var recyclerView: RecyclerView

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
        val inflated= inflater.inflate(R.layout.fragment_one, container, false)

        with(inflated){
            random_quote=findViewById(R.id.quote_textview)
            refresh_btn=findViewById(R.id.refresh_button)
            recyclerView=findViewById(R.id.recyclerView)
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewmodel:Fragment1_Activity= ViewModelProvider(this).get(Fragment1_Activity::class.java)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewmodel.quoteItem.observe(viewLifecycleOwner){result->
            random_quote.text=result
        }
        viewmodel.carItem.observe(viewLifecycleOwner){result->
            var adapter=MyAdapter1(result,this)
            recyclerView.adapter=adapter
        }
        refresh_btn.setOnClickListener{
            viewmodel.fetchQuote()
        }
    }

    override fun onItemClicked(data: Car) {
        val bundle=Bundle().apply {
            putParcelable("car",data)
        }
        val fragment2 = FragmentTwo.newInstance("", "")
        fragment2.arguments=bundle
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container1, fragment2, "fragment2")
            .commit()

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}