package com.example.cibs.service.utils

import android.app.Activity
import android.app.AlertDialog
import com.example.cibs.R

class LoadingDialog(val mActivity : Activity) {
    private lateinit var isdialog:AlertDialog
    fun startloading(){
        // set View
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_item, null)
        //set Dialog
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()

    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}