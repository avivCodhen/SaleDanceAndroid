package com.saledance.saledanceapp.view

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.saledance.saledanceapp.EXTRA_POST_TRANSITION_NAME
import com.saledance.saledanceapp.R
import com.saledance.saledanceapp.SALE
import com.saledance.saledanceapp.model.entities.Sale
import kotlinx.android.synthetic.main.activity_sale.*

class SaleActivity : AppCompatActivity() {

    private lateinit var sale: Sale
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sale = (intent.extras!!.getSerializable(SALE) as? Sale)!!

        val transitionName = intent.extras!!.getString(EXTRA_POST_TRANSITION_NAME)
        saleImage.transitionName = transitionName

        supportActionBar?.title = sale.name
        val decodedString = Base64.decode(sale.image, Base64.DEFAULT)

        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        saleImage.setImageBitmap(decodedByte)
    }
}
