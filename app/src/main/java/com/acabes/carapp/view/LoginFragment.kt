package com.acabes.carapp.view

import android.content.Context
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
import com.google.gson.Gson

class LoginFragment : Fragment() {
    lateinit var userNameTextView:EditText
    lateinit var passwordTextView:EditText
    lateinit var submitBtn: Button
    lateinit var errorMessage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflated= inflater.inflate(R.layout.fragment_login, container, false)
        with(inflated){
            userNameTextView=findViewById(R.id.username)
            passwordTextView=findViewById(R.id.password)
            submitBtn=findViewById(R.id.submit_btn)
            errorMessage=findViewById(R.id.error_in_validation)
        }
        return inflated
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        submitBtn.setOnClickListener {
            val username = userNameTextView.text.toString()
            val password = passwordTextView.text.toString()
            loginViewModel.nonEmptyValidation(username, password)
            loginViewModel.errorMsg.observe(viewLifecycleOwner) { result ->
                if (result.isNotEmpty())
                    errorMessage.text = result
                else
                    loginViewModel.loginAuth(username, password)
            }
            loginViewModel.responseData.observe(viewLifecycleOwner) { result ->
                if (result) {
                    errorMessage.text = ""
                    val intent = Intent(requireContext(), MainScreen::class.java)
                    startActivity(intent)
                } else {
                    errorMessage.text = "Invalid credentials"
                }
            }
            loginViewModel.responseDataValues.observe(viewLifecycleOwner){ result->
                try {
                    val sharedPreferences = requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)
                    val edits = sharedPreferences?.edit()
                    val JSONdata = Gson().toJson(result)
                    edits?.putString("data", JSONdata)
                    edits?.apply()
                }
                catch (e:Exception)
                {
                    println("${e.message}")
                }
            }
        }
    }
    companion object {
        @JvmStatic fun newInstance() = LoginFragment()
    }
}