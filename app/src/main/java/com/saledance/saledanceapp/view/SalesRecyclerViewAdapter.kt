package com.saledance.saledanceapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.view.ViewCompat
import com.saledance.saledanceapp.BASE_URL
import com.saledance.saledanceapp.EXTRA_POST_TRANSITION_NAME
import com.saledance.saledanceapp.IMAGE_URL
import com.saledance.saledanceapp.model.entities.Sale
import com.saledance.saledanceapp.view.interfaces.OnSaleClickListener
import com.squareup.picasso.Picasso
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
            val uniqueTransitionName = "$EXTRA_POST_TRANSITION_NAME$position"
            ViewCompat.setTransitionName(itemView.saleImage, uniqueTransitionName)
            itemView.saleImage.setOnClickListener{clickListener.onSaleClick(sale, itemView.saleImage, itemView.context)}
            Picasso
                .get()
                .load("$BASE_URL$IMAGE_URL${sale.imageId}")
                .into(itemView.saleImage)

            itemView.salePriceTv.text = String.format(res.getString
                (com.saledance.saledanceapp.R.string.discount_price),
                sale.salePrice!!.toInt().toString())
            itemView.beforePriceTv.text = String.format(res.getString
                (com.saledance.saledanceapp.R.string.original_price),
                sale.beforePrice!!.toInt().toString())    }
    }
}