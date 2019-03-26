package com.saledance.saledanceapp.view

import android.graphics.BitmapFactory
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saledance.saledanceapp.Extensions.toSimpleString
import com.saledance.saledanceapp.model.entities.PublishedPost
import com.saledance.saledanceapp.R
import kotlinx.android.synthetic.main.activity_business_details.*
import kotlinx.android.synthetic.main.post_item.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecyclerViewAdapter(
    private var publishedPosts: ArrayList<PublishedPost>,
    private val onPostClickListener: OnPostClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.PublishedPostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublishedPostViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PublishedPostViewHolder(
            inflatedView
        )
    }

    override fun getItemCount() = publishedPosts.size

    override fun onBindViewHolder(holder: PublishedPostViewHolder, position: Int) {

        holder.bindPost(publishedPosts[position], onPostClickListener, position)
    }


    class PublishedPostViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindPost(publishedPost: PublishedPost, clickListener: OnPostClickListener, position: Int) {

            itemView.postRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, true)
            itemView.postRecyclerView.adapter =
                PostRecyclerViewAdapter(publishedPost.sales)
            itemView.postBodyTv.text = publishedPost.post.body
            val transitionName = "test$position"
            ViewCompat.setTransitionName(itemView.businessAvatar, transitionName)
            itemView.businessBtn.setOnClickListener { clickListener.onPostClick(publishedPost.business, itemView.businessAvatar) }
            itemView.streetTv.text = publishedPost.business.address
            itemView.timeTv.text = toSimpleString(publishedPost.publishTime)
            val decodedString = Base64.decode(publishedPost.business.image, Base64.DEFAULT)
            itemView.businessTitleTV.text = publishedPost.business.name
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            itemView.businessAvatar.setImageBitmap(decodedByte)
        }

    }

}