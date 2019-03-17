package com.saledance.saledanceapp.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saledance.saledanceapp.model.entities.PublishedPost
import com.saledance.saledanceapp.R
import kotlinx.android.synthetic.main.post_item.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecyclerViewAdapter(
    public var publishedPosts: ArrayList<PublishedPost>,
    private val onPostClickListener: OnPostClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.PublishedPostViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PublishedPostViewHolder {
        val inflatedView = LayoutInflater.from(p0.context).inflate(R.layout.post_item, p0, false)
        return PublishedPostViewHolder(
            inflatedView,
            onPostClickListener
        )
    }

    override fun getItemCount() = publishedPosts.size

    override fun onBindViewHolder(p0: PublishedPostViewHolder, p1: Int) {
        val publishedPost = publishedPosts[p1]
        p0.itemView.postRecyclerView.layoutManager = LinearLayoutManager(p0.itemView.context,LinearLayoutManager.HORIZONTAL, true)
        p0.itemView.postRecyclerView.adapter =
            PostRecyclerViewAdapter(publishedPost.sales)
        p0.itemView.postBodyTv.text = publishedPosts[p1].post.body
        p0.itemView.setOnClickListener{onPostClickListener.onPostClick(publishedPost.business) }
        p0.itemView.streetTv.text = publishedPosts[p1].business.address
        p0.itemView.timeTv.text = publishedPost.publishTime
    }


    class PublishedPostViewHolder(
        itemView: View,
        onPostClickListener: OnPostClickListener
    ) : RecyclerView.ViewHolder(itemView) {

    }

}