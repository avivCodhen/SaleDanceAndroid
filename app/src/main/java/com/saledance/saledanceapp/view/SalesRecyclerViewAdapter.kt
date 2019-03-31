package com.saledance.saledanceapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.BitmapFactory
import android.support.v4.view.ViewCompat
import android.util.Base64
import com.saledance.saledanceapp.EXTRA_POST_TRANSITION_NAME
import com.saledance.saledanceapp.model.entities.Sale
import com.saledance.saledanceapp.view.interfaces.OnSaleClickListener
import kotlinx.android.synthetic.main.post_item.view.*
import kotlinx.android.synthetic.main.sale_item.view.*

class SalesRecyclerViewAdapter(private val sales : List<Sale>, private val onSaleClickListener: OnSaleClickListener) : RecyclerView.Adapter<SalesRecyclerViewAdapter.SaleHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SaleHolder {
        val inflatedView = LayoutInflater.from(p0.context).inflate(com.saledance.saledanceapp.R.layout.sale_item, p0, false)
        return SaleHolder(inflatedView)
    }

    override fun getItemCount() = sales.size


    override fun onBindViewHolder(holder: SaleHolder, position: Int) {
        holder.bindSale(sales[position], onSaleClickListener, position)

    }



    inner class SaleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindSale(
            sale: Sale,
            clickListener: OnSaleClickListener,
            position: Int
        ) {
            val res = itemView.context.resources
            val decodedString = Base64.decode(sale.image, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            val uniqueTransitionName = "$EXTRA_POST_TRANSITION_NAME$position"
            ViewCompat.setTransitionName(itemView.saleImage, uniqueTransitionName)
            itemView.saleImage.setOnClickListener{clickListener.onSaleClick(sale, itemView.saleImage, itemView.context)}
            itemView.saleImage.setImageBitmap(decodedByte)
            itemView.salePriceTv.text = String.format(res.getString
                (com.saledance.saledanceapp.R.string.discount_price),
                sale.salePrice.toInt().toString())
            itemView.beforePriceTv.text = String.format(res.getString
                (com.saledance.saledanceapp.R.string.original_price),
                sale.beforePrice.toInt().toString())    }
    }
}