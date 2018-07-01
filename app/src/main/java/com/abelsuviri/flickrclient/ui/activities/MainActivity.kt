package com.abelsuviri.flickrclient.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.abelsuviri.data.model.Entry
import com.abelsuviri.data.model.EntryParcel
import com.abelsuviri.data.model.Feed
import com.abelsuviri.flickrclient.R
import com.abelsuviri.flickrclient.ui.adapters.PicturesAdapter
import com.abelsuviri.flickrclient.ui.adapters.holders.IPictureClick
import com.abelsuviri.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Abel Suviri
 */

class MainActivity : AppCompatActivity(), IPictureClick {

    private var listStatusKey = "LIST_STATUS"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var picturesGrid: RecyclerView

    private lateinit var loading: RelativeLayout

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private var listStatus: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        initViews()
        subscribeLiveData()
        loadPictures()
        setupGrid()
    }

    /**
     * Save the layout state of the RecyclerView
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelable(listStatusKey, layoutManager.onSaveInstanceState())
    }

    /**
     * Restore the layout state of the RecyclerView
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        listStatus = savedInstanceState!!.getParcelable(listStatusKey)
    }

    override fun onResume() {
        super.onResume()
        if (listStatus == null) {
            layoutManager.onRestoreInstanceState(listStatus)
        }
    }

    /**
     * Bind the views
     */
    private fun initViews() {
        swipeRefreshLayout = refreshLayout
        picturesGrid = picturesList
        loading = loadingLayout

        swipeRefreshLayout.setOnRefreshListener { loadPictures() }
    }

    /**
     * Subscribe to the LiveData objects from the ViewModel and perform the actions depending the result
     */
    private fun subscribeLiveData() {
        mainViewModel.isLoading.observe(this, Observer<Boolean> { isLoading -> loading.visibility = if (isLoading!!) View.VISIBLE else View.GONE })
        mainViewModel.isFailing.observe(this, Observer<Boolean> { isFailing ->  if (isFailing!!) showRetryDialog()})
        mainViewModel.feed.observe(this, Observer<Feed> { feed ->
            picturesGrid.adapter = PicturesAdapter(feed!!.entry!!, this)
            swipeRefreshLayout.isRefreshing = false
        })
    }

    /**
     * Call the service to retrieve the pictures
     */
    private fun loadPictures() {
        mainViewModel.requestFeed()
    }

    /**
     * Create the layout manager for the RecyclerView
     */
    private fun setupGrid() {
        layoutManager = GridLayoutManager(this, 2)
        picturesGrid.layoutManager = layoutManager
    }

    /**
     * Handle the click event on a picture from the grid to open the detail screen.
     */
    override fun onPictureClick(entry: Entry, picture: ImageView) {
        val intent = Intent(this, PictureDetailActivity::class.java)
        val parcel = EntryParcel.create(entry.title, entry.link!![entry.link!!.size - 1].href, entry.author!!.name, entry.published)
        intent.putExtra(PictureDetailActivity.detailBundle, parcel)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, picture, getString(R.string.image_transition))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    /**
     * Display an AlertDialog to try again to call the server.
     */
    private fun showRetryDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage(resources.getString(R.string.request_error))
        builder.setCancelable(false)
        builder.setPositiveButton(resources.getString(R.string.retry)) { dialog, i ->
            dialog.dismiss()
            loadPictures()
        }.show()
    }
}
