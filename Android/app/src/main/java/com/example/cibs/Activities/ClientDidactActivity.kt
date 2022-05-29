package com.example.cibs.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.example.cibs.R
import com.example.cibs.Adapters.RestoViewPageprivateAdapter

class ClientDidactActivity : AppCompatActivity() {

    private val texttitleList = mutableListOf<String>("Faites un tour dans l'art culinnaire africain et ses different plat", "commander vos different repas en toutes securite", "Faite vous livrer a domicile en Express dans le respect des termes")
    private val textdetailList = mutableListOf<String>("pret a tenter l'experience ?", "etes vous d'accord ?", "vous y est presque")
    private val imageList = mutableListOf<Int>(
        R.drawable.repas_afrique,
        R.drawable.commander2,
        R.drawable.livraison
    )
    var count: Int  = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_didact)

        val  viewPager = findViewById<ViewPager2>(R.id.view_pager2)
        viewPager.adapter = RestoViewPageprivateAdapter(texttitleList, imageList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        var btnCountinu  = findViewById<AppCompatButton>(R.id.btnTrue)

        count  = 0
        btnCountinu.setOnClickListener {
            viewPager.apply {
                if(count<3){
                    count++
                    beginFakeDrag()
                    fakeDragBy(-10f)
                    endFakeDrag()
                    if(count == 2){
                        btnCountinu.text = "Terminer"
                    }
                }
                else{

                }
                count++

            }
        }
    }
}