package com.example.cibs.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.example.cibs.R
import com.example.cibs.Adapters.RestoViewPageprivateAdapter

class RestoDidactActivity : AppCompatActivity() {

    private val texttitleList = mutableListOf<String>("Inscrivez votre restaurant sur notre application et faites un max de gain", "fournit une alimentation saine et equilibre a vos client", "Respect la loi et contravention sur les restaurants au cameroun")
    private val textdetailList = mutableListOf<String>("pret a tenter l'experience ?", "etes vous d'accord ?", "vous y est presque")
    private val imageList = mutableListOf<Int>(
        R.drawable.money2,
        R.drawable.saine2,
        R.drawable.loi2
    )
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resto_didact)

        val  viewPager = findViewById<ViewPager2>(R.id.view_pager2)
        viewPager.adapter = RestoViewPageprivateAdapter(texttitleList, imageList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        var btnCountinu  = findViewById<AppCompatButton>(R.id.btnTrue)

        count = 0
        btnCountinu.setOnClickListener {
            viewPager.apply {
                if(count<2){
                    count++
                    beginFakeDrag()
                    fakeDragBy(-10f)
                    endFakeDrag()

                    if(count == 2){
                        btnCountinu.text = "Terminer"
                    }
                }
                else{
                    Intent(applicationContext, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                    finish()
                }


            }
        }

    }
}