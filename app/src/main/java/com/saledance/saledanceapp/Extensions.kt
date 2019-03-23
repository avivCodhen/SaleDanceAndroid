package com.saledance.saledanceapp

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*

object Extensions {

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    fun toSimpleString(publishedAt: String): String {
        val inputFormatter = SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ", Locale.ENGLISH)
        val outputFormatter = SimpleDateFormat ("EEE d MMM", Locale.getDefault())
        val date = inputFormatter.parse(publishedAt)
        return outputFormatter.format(date)
    }
}