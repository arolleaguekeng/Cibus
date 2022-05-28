package com.example.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.cibs.Interfaces.ItemClickListener
import com.example.cibs.viewModel.ViewPageAdapter
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity(), ItemClickListener {

    private val texttitleList = mutableListOf<String>("Gerer un restaurant", "commander vos menu")
    private val textButtonList = mutableListOf<String>("Commencer", "Decouvrir")
    private val imageList = mutableListOf<Int>(R.drawable.r5, R.drawable.r1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  viewPager = findViewById<ViewPager2>(R.id.view_pager2)
        viewPager.adapter = ViewPageAdapter(texttitleList, textButtonList, imageList, this)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewPager)
    }

    override fun onButtonClicked(position: Int) {
        if(position == 0){
            Intent(applicationContext, RestoDidactActivity::class.java).also {
                startActivity(it)
            }
        }
        else{
            Intent(applicationContext, ClientDidactActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}