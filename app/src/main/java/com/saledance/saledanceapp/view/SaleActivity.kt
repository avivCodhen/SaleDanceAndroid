package com.saledance.saledanceapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.saledance.saledanceapp.*
import com.saledance.saledanceapp.model.entities.Sale
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sale.*

class SaleActivity : AppCompatActivity() {

    private lateinit var sale: Sale
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sale = (intent.extras!!.getParcelable(SALE) as? Sale)!!

        val transitionName = intent.extras!!.getString(EXTRA_POST_TRANSITION_NAME)
        saleImage.transitionName = transitionName

        supportActionBar?.title = sale.name

        Picasso
            .get()
            .load("$BASE_URL$IMAGE_URL${sale.imageId}")
            .into(saleImage)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
