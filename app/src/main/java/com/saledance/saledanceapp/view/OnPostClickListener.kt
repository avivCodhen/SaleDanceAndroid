package com.saledance.saledanceapp.view

import android.widget.ImageView
import com.saledance.saledanceapp.model.entities.Business

interface OnPostClickListener {

    fun onPostClick(b : Business, imageView: ImageView)
}