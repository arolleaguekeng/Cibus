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

class LoginActivityViewModel: ViewModel() {

    var loginNewUserLiveData: MutableLiveData<MutableList<User>?> = MutableLiveData()

    fun getLoginUserNewObservable(): MutableLiveData<MutableList<User>?>
    {
        return loginNewUserLiveData
    }


    fun LoginUser(email: String, password: String){
        val retroInstance = RetroInstance.getRetroInstance().create(UserServiceApi::class.java)
        val call = retroInstance.getUserLogin(email, password)

        call.enqueue(object : Callback<MutableList<User>?> {
            override fun onResponse(call: Call<MutableList<User>?>, response: Response<MutableList<User>?>)
            {
                if(response.isSuccessful)
                {
                    loginNewUserLiveData.postValue(response.body())
                }
                else
                {
                    loginNewUserLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<MutableList<User>?>, t: Throwable)
            {
                loginNewUserLiveData.postValue(null)
            }

        })

    }
}