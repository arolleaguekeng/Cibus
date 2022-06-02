package com.example.cibs.viewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cibs.RetroInstance
import com.example.cibs.model.*
import com.example.cibs.model.detailRestaurant.Repas
import com.example.cibs.service.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivityViewModel: ViewModel() {

    var restaurantLiveData: MutableLiveData<MutableList<Restaurant>?> = MutableLiveData()
    var repasLiveData: MutableLiveData<MutableList<Plat>?> = MutableLiveData()
    var categorieLiveData: MutableLiveData<MutableList<Categorie>?> = MutableLiveData()
    var addProductPanierLiveData: MutableLiveData<ProductResponse?> = MutableLiveData()
    var getAllProductpanierLiveData: MutableLiveData<MutableList<ProduitPanier>?> = MutableLiveData()
    var getAllLocationLiveDate: MutableLiveData<MutableList<Localisation>?> = MutableLiveData()

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

    fun getLocationObservable(): MutableLiveData<MutableList<Localisation>?>
    {
        return getAllLocationLiveDate
    }


    fun addProductNewObservable(): MutableLiveData<ProductResponse?>
    {
        return addProductPanierLiveData
    }

    fun getAllProductpanierObservable(): MutableLiveData<MutableList<ProduitPanier>?>
    {
        return getAllProductpanierLiveData
    }


    fun getLocalisation(){
        val retroInstance = RetroInstance.getRetroInstance().create(LocalisationServiceApi::class.java)
        val call = retroInstance.getAllLocalisation()


        call.enqueue(object : Callback<MutableList<Localisation>?> {
            override fun onResponse(call: Call<MutableList<Localisation>?>, response: Response<MutableList<Localisation>?>)
            {
                if(response.isSuccessful)
                {
                    getAllLocationLiveDate.postValue(response.body())
                }
                else
                {
                    getAllLocationLiveDate.postValue(null)
                }
            }
            override fun onFailure(call: Call<MutableList<Localisation>?>, t: Throwable)
            {
                getAllLocationLiveDate.postValue(null)
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





    fun addProductPanier(product: ProduitPanier){
        val retroInstance = RetroInstance.getRetroInstance().create(ProductServiceApi::class.java)
        val call = retroInstance.AddProduitPanier(product)

        call.enqueue(object : Callback<ProductResponse?> {
            override fun onResponse(
                call: Call<ProductResponse?>,
                response: Response<ProductResponse?>
            ) {
                if (response.isSuccessful) {
                    addProductPanierLiveData.postValue(response.body())
                } else {
                    addProductPanierLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                addProductPanierLiveData.postValue(null)
            }

        })

    }


    fun GetAllProductPanier(){
        val retroInstance = RetroInstance.getRetroInstance().create(ProductServiceApi::class.java)
        val call = retroInstance.getAllProduitPanier()

        call.enqueue(object : Callback<MutableList<ProduitPanier>?> {
            override fun onResponse(call: Call<MutableList<ProduitPanier>?>, response: Response<MutableList<ProduitPanier>?>)
            {
                if(response.isSuccessful)
                {
                    getAllProductpanierLiveData.postValue(response.body())
                }
                else
                {
                    getAllProductpanierLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<MutableList<ProduitPanier>?>, t: Throwable)
            {
                getAllProductpanierLiveData.postValue(null)
            }

        })

    }

}
