package com.acabes.carapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.acabes.carapp.R
import com.acabes.carapp.view_model.LoginViewModel

class LoginFragment : Fragment() {
    lateinit var userName:EditText
    lateinit var password:EditText
    lateinit var submitBtn: Button
    lateinit var errorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflated= inflater.inflate(R.layout.fragment_login, container, false)
        with(inflated){
            userName=findViewById(R.id.username)
            password=findViewById(R.id.password)
            submitBtn=findViewById(R.id.submit_btn)
            errorMessage=findViewById(R.id.error_in_validation)
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginViewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
        submitBtn.setOnClickListener{
            if(nonEmptyValidation()){
                val username=userName.text.toString()
                val pass=password.text.toString()
                loginViewModel.loginAuth(username,pass)
                }
            }
        loginViewModel.responseData.observe(viewLifecycleOwner){ result->
            if(result){
                errorMessage.text=""
                val intent= Intent(requireContext(),MainScreen::class.java)
                startActivity(intent)
            }
            else{
                errorMessage.text="Invalid credentials"
            }
        }

    }
    fun nonEmptyValidation():Boolean{
        var result=false
        if(userName.text.isEmpty())
            errorMessage.text="Username cannot be empty"
        else if (password.text.isEmpty())
            errorMessage.text="Password cannot be empty"
        else
            result=true
        return result
    }
    companion object {
        @JvmStatic fun newInstance() = LoginFragment()
    }
}