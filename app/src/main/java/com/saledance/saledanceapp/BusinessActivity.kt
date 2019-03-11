package com.saledance.saledanceapp

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.sale_item.view.*

class BusinessActivity : AppCompatActivity() {

    private lateinit var business :Business;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)


        business = (intent.extras!!.getSerializable("Business") as? Business)!!
        val decodedString = Base64.decode(business.image, Base64.DEFAULT)

        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        businessImage.setImageBitmap(decodedByte)

    }
}
