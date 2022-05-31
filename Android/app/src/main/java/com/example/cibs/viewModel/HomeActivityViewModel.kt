package com.example.cibs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cibs.RetroInstance
import com.example.cibs.model.*
import com.example.cibs.model.detailRestaurant.Repas
import com.example.cibs.service.CategorieServiceApi
import com.example.cibs.service.PlatServiceApi
import com.example.cibs.service.RestoServiceApi
import com.example.cibs.service.UserServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivityViewModel: ViewModel() {

    var restaurantLiveData: MutableLiveData<MutableList<Restaurant>?> = MutableLiveData()
    var repasLiveData: MutableLiveData<MutableList<Plat>?> = MutableLiveData()
    var categorieLiveData: MutableLiveData<MutableList<Categorie>?> = MutableLiveData()

    fun getRestaurantNewObservable(): MutableLiveData<MutableList<Restaurant>?>
    {
        return restaurantLiveData
    }

    fun getRepasNewObservable(): MutableLiveData<MutableList<Plat>?>
    {
        return repasLiveData
    }

    fun getCategoryObservable(): MutableLiveData<MutableList<Categorie>?>
    {
        return categorieLiveData
    }


    fun GetRestaurant(){
        val retroInstance = RetroInstance.getRetroInstance().create(RestoServiceApi::class.java)
        val call = retroInstance.getAllRestaurant()

        call.enqueue(object : Callback<MutableList<Restaurant>?> {
            override fun onResponse(call: Call<MutableList<Restaurant>?>, response: Response<MutableList<Restaurant>?>)
            {
                if(response.isSuccessful)
                {
                    restaurantLiveData.postValue(response.body())
                }
                else
                {
                    restaurantLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<MutableList<Restaurant>?>, t: Throwable)
            {
                restaurantLiveData.postValue(null)
            }

        })

    }

    fun GetPlat(){
        val retroInstance = RetroInstance.getRetroInstance().create(PlatServiceApi::class.java)
        val call = retroInstance.getAllRepas()

        call.enqueue(object : Callback<MutableList<Plat>?> {
            override fun onResponse(call: Call<MutableList<Plat>?>, response: Response<MutableList<Plat>?>)
            {
                if(response.isSuccessful)
                {
                    repasLiveData.postValue(response.body())
                }
                else
                {
                    repasLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<MutableList<Plat>?>, t: Throwable)
            {
                repasLiveData.postValue(null)
            }

        })

    }

    fun GetCategorie(){
        val retroInstance = RetroInstance.getRetroInstance().create(CategorieServiceApi::class.java)
        val call = retroInstance.getAllCategorie()

        call.enqueue(object : Callback<MutableList<Categorie>?> {
            override fun onResponse(call: Call<MutableList<Categorie>?>, response: Response<MutableList<Categorie>?>)
            {
                if(response.isSuccessful)
                {
                    categorieLiveData.postValue(response.body())
                }
                else
                {
                    categorieLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<MutableList<Categorie>?>, t: Throwable)
            {
                categorieLiveData.postValue(null)
            }

        })

    }
}