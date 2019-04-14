package com.saledance.saledanceapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.saledance.saledanceapp.*
import com.saledance.saledanceapp.Extensions.toSimpleString
import com.saledance.saledanceapp.model.entities.PublishedPost
import com.saledance.saledanceapp.model.entities.Sale
import com.saledance.saledanceapp.view.interfaces.OnPostClickListener
import com.saledance.saledanceapp.view.interfaces.OnSaleClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*
import com.squareup.picasso.Callback


class PostRecyclerViewAdapter(
    private var publishedPosts: ArrayList<PublishedPost>,
    private val onPostClickListener: OnPostClickListener) :
    RecyclerView.Adapter<PostRecyclerViewAdapter.PublishedPostViewHolder>(), OnSaleClickListener {


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


    inner class PublishedPostViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindPost(publishedPost: PublishedPost, clickListener: OnPostClickListener, position: Int) {

            itemView.postRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, true)
            itemView.postRecyclerView.adapter =
                SalesRecyclerViewAdapter(publishedPost.sales, this@PostRecyclerViewAdapter)
            itemView.postBodyTv.text = publishedPost.post.body
            val uniqueTransitionName = "$EXTRA_POST_TRANSITION_NAME$position"
            ViewCompat.setTransitionName(itemView.businessAvatar, uniqueTransitionName)
            itemView.businessBtn.setOnClickListener {clickListener.onPostClick(publishedPost.business, itemView.businessAvatar) }
            itemView.streetTv.text = publishedPost.business.address
            itemView.timeTv.text = toSimpleString(publishedPost.publishTime)

            Picasso
                .get()
                .load("$BASE_URL$IMAGE_URL${publishedPost.business.imageId}")
                .placeholder(R.drawable.ic_photo_black_24dp)
                .noFade()
                .into(itemView.businessAvatar, object : Callback {
                override fun onSuccess() {
                    itemView.businessAvatar.alpha = 0f
                    itemView.businessAvatar.animate().setDuration(1000).alpha(1f).start()
                }

                override fun onError(e: Exception) {}
            })
        }
    }

    override fun onSaleClick(sale: Sale, imageView: ImageView, context: Context) {
        val intent  = Intent(context, SaleActivity::class.java)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(context as Activity, imageView, ViewCompat.getTransitionName(imageView)!!)
        intent.putExtra(SALE, sale)
        intent.putExtra(EXTRA_POST_TRANSITION_NAME, ViewCompat.getTransitionName(imageView))
        context.startActivity(intent, options.toBundle())
    }

}