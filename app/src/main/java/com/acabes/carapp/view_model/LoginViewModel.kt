package com.acabes.carapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acabes.carapp.model.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel(){
    var responseData=MutableLiveData<Boolean>()
    val loginAPI=RetrofitHelper().getUserInstance().create(APIinterface::class.java)
    fun loginAuth(username:String ,password:String){
        val loginRequest=LoginRequest(username,password)
        viewModelScope.launch{
            try{
                responseData.value=loginAPI.login(loginRequest).isSuccessful
            }catch(e:Exception){
                e.printStackTrace()
            }
        }

    }
}
