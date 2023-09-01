package com.acabes.carapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acabes.carapp.model.LoginRequest
import com.acabes.carapp.model.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel(){
    var errorMsg=MutableLiveData<String>()
    var responseData=MutableLiveData<Boolean>()
    var responseDataValues=MutableLiveData<LoginResponse>()
    val loginAPI=RetrofitHelper().getUserInstance().create(APIinterface::class.java)
    fun nonEmptyValidation(username: String, password: String){
        if(username.isEmpty())
            errorMsg.value="Username cannot be empty"
        else if(password.isEmpty())
            errorMsg.value="Password cannot be empty"
        else
            errorMsg.value=""
    }
    fun loginAuth(username:String ,password:String){
        val loginRequest=LoginRequest(username,password)
        viewModelScope.launch{
            try{
                val result=loginAPI.login(loginRequest)
                responseData.value=result.isSuccessful
                responseDataValues.value=result.body()
            }catch(e:Exception){
                e.printStackTrace()
            }
        }

    }
}
