package com.saledance.saledanceapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.sale_item.view.*
import android.graphics.BitmapFactory
import android.util.Base64
import com.saledance.saledanceapp.R
import com.saledance.saledanceapp.model.entities.Sale
import kotlinx.android.synthetic.main.post_item.view.*
import java.time.LocalDate


class PostRecyclerViewAdapter(private val sales : List<Sale>) : RecyclerView.Adapter<PostRecyclerViewAdapter.SaleHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SaleHolder {
        val inflatedView = LayoutInflater.from(p0.context).inflate(R.layout.sale_item, p0, false)
        return SaleHolder(inflatedView)
    }

    override fun getItemCount() = sales.size


    override fun onBindViewHolder(p0: SaleHolder, p1: Int) {
        val sale = sales.get(p1)
        val decodedString = Base64.decode(sale.image, Base64.DEFAULT)

        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        p0.itemView.saleImage.setImageBitmap(decodedByte)
        p0.itemView.salePriceTv.text = "₪"+sale.salePrice.toInt().toString()
        p0.itemView.beforePriceTv.text = "₪"+sale.beforePrice.toInt().toString() + " במקום"

    }

    class SaleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}