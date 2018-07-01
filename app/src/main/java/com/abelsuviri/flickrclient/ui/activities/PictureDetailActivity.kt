package com.abelsuviri.flickrclient.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.abelsuviri.data.model.Entry
import com.abelsuviri.data.model.EntryParcel
import com.abelsuviri.data.utils.DateParser
import com.abelsuviri.flickrclient.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_picture_detail.*

/**
 * @author Abel Suviri
 */

class PictureDetailActivity : AppCompatActivity() {

    companion object {
        const val detailBundle = "DETAIL_BUNDLE"
    }

    private lateinit var titleTextView: TextView

    private lateinit var authorTextView: TextView

    private lateinit var publishDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_detail)

        initViews()
        fillViews()
    }

    /**
     * Bind the views
     */
    private fun initViews() {
        titleTextView = titleText
        authorTextView = author
        publishDateTextView = publishDate
    }

    private fun fillViews() {
        val entry: EntryParcel = intent.getParcelableExtra(detailBundle)

        titleTextView.text = entry.title
        authorTextView.text = entry.author
        publishDateTextView.text = DateParser.parseDate(entry.publishDate)

        Picasso.get()
                .load(entry.link)
                .noFade()
                .into(picture, object : Callback {
                    override fun onSuccess() {
                        supportStartPostponedEnterTransition()
                    }

                    override fun onError(e: Exception) {
                        supportStartPostponedEnterTransition()
                    }
                })
    }
}
