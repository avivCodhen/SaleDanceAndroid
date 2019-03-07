package com.saledance.saledanceapp

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_item.view.*

class RecyclerViewAdapter(
    private val publishedPosts: ArrayList<PublishedPost>,
    private val onPostclickListener: OnPostClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.PublishedPostViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerViewAdapter.PublishedPostViewHolder {
        val inflatedView = LayoutInflater.from(p0.context).inflate(R.layout.post_item, p0, false)
        return PublishedPostViewHolder(inflatedView, onPostclickListener)
    }

    override fun getItemCount() = publishedPosts.size

    override fun onBindViewHolder(p0: RecyclerViewAdapter.PublishedPostViewHolder, p1: Int) {
        val publishedPost = publishedPosts.get(p1)
        p0.itemView.postRecyclerView.layoutManager = LinearLayoutManager(p0.itemView.context,LinearLayoutManager.HORIZONTAL, true)
        p0.itemView.postRecyclerView.adapter = PostRecyclerViewAdapter(publishedPost.sales)
        p0.itemView.postTitle.text = publishedPosts.get(p1).post.name
        p0.itemView.postBodyTv.text = publishedPosts.get(p1).post.body
        p0.itemView.setOnClickListener{onPostclickListener.OnPostClick(publishedPost.business) }
    }


    class PublishedPostViewHolder(
        itemView: View,
        onPostclickListener: OnPostClickListener
    ) : RecyclerView.ViewHolder(itemView) {

    }

}