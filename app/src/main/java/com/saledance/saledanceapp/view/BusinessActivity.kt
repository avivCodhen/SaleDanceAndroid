package com.saledance.saledanceapp.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import android.view.View
import com.saledance.saledanceapp.model.entities.Business
import kotlinx.android.synthetic.main.activity_business_details.*
import com.saledance.saledanceapp.EXTRA_POST_TRANSITION_NAME
import android.support.v4.view.ViewCompat.setTransitionName




class BusinessActivity : AppCompatActivity() {

    private lateinit var business: Business
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.saledance.saledanceapp.R.layout.activity_business_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        business = (intent.extras!!.getSerializable("Business") as? Business)!!

        val transitionName = intent.extras!!.getString(EXTRA_POST_TRANSITION_NAME)
        businessImage.transitionName = transitionName

        supportActionBar?.title = business.name
        val decodedString = Base64.decode(business.image, Base64.DEFAULT)

        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        businessImage.setImageBitmap(decodedByte)

        businessPhone.text = business.businessPhoneContact
        businessMail.text = business.businessEmailContact
        businessSite.text = business.site
        businessInfo.text = business.about
        weekDaysHours.text = business.weekDays
        fridayHours.text = business.friday
        saturdayHours.text = business.saturday
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

    fun itemClick(view: View) {

        when(view.id) {
            com.saledance.saledanceapp.R.id.businessPhone -> {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${business.businessPhoneContact}")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
            com.saledance.saledanceapp.R.id.businessMail -> {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${business.businessEmailContact}")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
            com.saledance.saledanceapp.R.id.businessSite -> {
                val webpage: Uri = Uri.parse(business.site)
                val intent = Intent(Intent.ACTION_VIEW, webpage)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
    }
}
