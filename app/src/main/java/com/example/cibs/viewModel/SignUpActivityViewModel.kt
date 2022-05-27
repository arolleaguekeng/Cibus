package com.example.cibs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cibs.RetroInstance
import com.example.cibs.model.User
import com.example.cibs.model.UserResponse
import com.example.cibs.service.UserServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivityViewModel : ViewModel(){

    var SignUpNewUserLiveData: MutableLiveData<UserResponse?> = MutableLiveData()

    fun getSignUpUserNewObservable(): MutableLiveData<UserResponse?>
    {
        return SignUpNewUserLiveData
    }

    fun SignUpUser(user: User){
        val retroInstance = RetroInstance.getRetroInstance().create(UserServiceApi::class.java)
        val call = retroInstance.createUser(user)

        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>)
            {
                if(response.isSuccessful)
                {
                    SignUpNewUserLiveData.postValue(response.body())
                }
                else
                {
                    SignUpNewUserLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<UserResponse?>, t: Throwable)
            {
                SignUpNewUserLiveData.postValue(null)
            }

        })

    }

}