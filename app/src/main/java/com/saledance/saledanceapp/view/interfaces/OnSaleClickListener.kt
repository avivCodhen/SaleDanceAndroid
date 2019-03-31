package com.saledance.saledanceapp.view.interfaces

import android.content.Context
import android.widget.ImageView
import com.saledance.saledanceapp.model.entities.Business
import com.saledance.saledanceapp.model.entities.Sale

interface OnSaleClickListener {

    fun onSaleClick(sale : Sale, imageView: ImageView, context: Context)
}