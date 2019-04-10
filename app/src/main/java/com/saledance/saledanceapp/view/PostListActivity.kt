package com.saledance.saledanceapp.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.ImageView
import android.widget.Toast
import com.saledance.saledanceapp.*
import com.saledance.saledanceapp.model.entities.Business
import com.saledance.saledanceapp.model.entities.PublishedPost
import com.saledance.saledanceapp.view.interfaces.OnPostClickListener
import com.saledance.saledanceapp.viewmodel.BusinessListViewModel
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.view.animation.AnimationUtils


class PostListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnPostClickListener {

    override fun onPostClick(b: Business, imageView: ImageView) {
        val intent  = Intent(this@PostListActivity, BusinessActivity::class.java)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, imageView, ViewCompat.getTransitionName(imageView)!!)
        intent.putExtra(BUSINESS, b)
        intent.putExtra(EXTRA_POST_TRANSITION_NAME, ViewCompat.getTransitionName(imageView))
        startActivity(intent, options.toBundle())
    }

    private lateinit var model: BusinessListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PostRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

      /*  val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
*/
        nav_view.setNavigationItemSelectedListener(this)

        model = ViewModelProviders.of(this).get(BusinessListViewModel::class.java)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        val deco = DividerItemDecoration(this@PostListActivity, SCROLL_AXIS_HORIZONTAL)
        recyclerView.addItemDecoration(deco)
        observeViewModel()
    }

    private fun observeViewModel() {

        model.getBusinesses().observe(this, Observer<ArrayList<PublishedPost>>{ list ->
            if (list!!.size > 0){
                createRecyclerView(list!!)
            }else{
                tvNoResults.visibility = VISIBLE
            }
        })


        model.getError().observe(this, Observer<Throwable>{ error ->
            Toast.makeText(this, error?.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.refresh -> {
                recyclerView.adapter = null
                postActivityLoader.visibility = VISIBLE
               model.loadBusinesses()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun createRecyclerView(list : ArrayList<PublishedPost>){
        adapter = PostRecyclerViewAdapter(list, this)
        recyclerView.adapter = adapter
        val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        recyclerView.layoutAnimation = animation
        postActivityLoader.visibility = INVISIBLE

    }
}
