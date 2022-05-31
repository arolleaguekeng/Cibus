package com.example.cibs.Activities

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import cm.pam.retos.RepasApi
import com.example.cibs.service.DetailRestaurant.RestaurantApi
import com.example.cibs.Adapters.DetailRestaurant.ViewPagerAdapter
import com.example.cibs.R
import com.example.cibs.model.detailRestaurant.Repas
import com.example.cibs.model.Restaurant
import com.example.cibs.model.RestaurantModel
import com.example.cibs.service.DetailRestaurant.Retro
import com.example.cibs.service.utils.LoadingDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailRestaurantActivity : AppCompatActivity(){
    companion object{
        var baseImageUrl = "http://192.168.1.107:8000/static/"
        var restauxList = mutableListOf<RestaurantModel>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_detail_restaurant)
        val loading = LoadingDialog(this)
        loading.startloading()
        val handler = Handler()

        //pour recupérer la liste des Restaurants
//        try{
            val retro = Retro().getRetoClient().create(RestaurantApi::class.java)
            retro.getRestaurant().enqueue(object : Callback<MutableList<Restaurant>>{
                //si tout ce passe bien
                override fun onResponse(
                    call: Call<MutableList<Restaurant>>,
                    response: Response<MutableList<Restaurant>>
                ) {
                    println("------------==================----------------------====================-----"+response.body().toString())
                    if(response.isSuccessful){
                        var i=0
                        for(rest in response.body()!!){

                            //pour ajouter l'Url au nom de l'image
                            rest.image = baseImageUrl+rest.image

                            //recupérer tous les Repas
                            val retro = Retro().getRetoClient().create(RepasApi::class.java)
                            retro.getRepasRestautant().enqueue(object : Callback<MutableList<Repas>>{
                                override fun onResponse(
                                    call: Call<MutableList<Repas>>,
                                    response: Response<MutableList<Repas>>
                                ) {
                                    if (response.isSuccessful){
                                        var repass = mutableListOf<Repas>()

                                        //parcourrir la liste des Repas
                                        for(repas in response.body()!!){
                                            //trier les Repas par restaurant (retourne pour chaque restorant sa liste de repas en fonction de l'id)
                                            if(repas.restaurant_id == rest.restaurant_id){
                                                repas.image = baseImageUrl+repas.image
                                                Log.e("image:" ,repas.image)
                                                repass.add(repas)
                                            }
                                        }
                                        //transforme la classe RestorantModel en Restorant
                                        var resto = RestaurantModel(rest, repass)

                                        //ajoute le les restorants qui ont des repas dans la liste des restaurants
                                        restauxList.add(resto)
                                    }

                                }

                                override fun onFailure(call: Call<MutableList<Repas>>, t: Throwable) {
                                    //    println("******************************////////////******************************"+t.message)
                                }
                            })
                            // println("******************************////////////******************************"+q)
                            i++
                        }

                    }

                    val view_pager2 : ViewPager2 = findViewById(R.id.view_pager2)
                    //retrais des données fictives
//                restauxList.removeAt(0)
//                restauxList.removeAt(0)
//                restauxList.removeAt(1)
                    view_pager2.adapter = ViewPagerAdapter(restauxList)

                    view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    loading.isDismiss()
                }

                override fun onFailure(call: Call<MutableList<Restaurant>>, t: Throwable) {
                    println("------------==================----------------------====================-----"+t.message)
                    Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                }

            })
//        }
//        catch (e:Exception){
//            Toast.makeText(applicationContext,e.message,Toast.LENGTH_LONG).show()
//            val refresh: Intent = Intent(this, DetailRestaurantActivity::class.java)
//            startActivity(refresh)
//        }

    }
}


