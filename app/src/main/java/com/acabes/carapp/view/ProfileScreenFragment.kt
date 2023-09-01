package com.acabes.carapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.acabes.carapp.R
import com.acabes.carapp.model.LoginResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ProfileScreenFragment : Fragment() {
    lateinit var firstNameTextView: TextView
    lateinit var lastNameTextView: TextView
    lateinit var emailTextView: TextView
    lateinit var userNameTextView: TextView
    lateinit var genderTextView: TextView
    lateinit var accountImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflated= inflater.inflate(R.layout.fragment_profile_screen, container, false)
        with(inflated){
            firstNameTextView=findViewById(R.id.first_name)
            lastNameTextView=findViewById(R.id.last_name)
            emailTextView=findViewById(R.id.email_id)
            userNameTextView=findViewById(R.id.user_name)
            genderTextView=findViewById(R.id.gender)
            accountImageView=findViewById(R.id.account_image)
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences=requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val finalData=sharedPreferences?.getString("data",null)
        val values = Gson().fromJson(finalData,LoginResponse::class.java)
        userNameTextView.text=values.username
        firstNameTextView.text=values.firstName
        lastNameTextView.text=values.lastName
        emailTextView.text=values.email
        genderTextView.text=values.gender
        Picasso.get()
            .load(values.image)
            .placeholder(R.drawable.profile_icon)
            .error(R.drawable.profile_icon)
            .into(accountImageView)
    }
    companion object {
        @JvmStatic
        fun newInstance() = ProfileScreenFragment()
    }
}