package com.abelsuviri.flickrclient.ui.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.abelsuviri.data.model.Entry
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture_item.view.*

/**
 * @author Abel Suviri
 */

class PictureViewHolder constructor(view: View, private val pictureClick: IPictureClick): RecyclerView.ViewHolder(view) {
    fun bind(entry: Entry) {
        val width = itemView.context.resources.displayMetrics.widthPixels
        Picasso.get()
                .load(entry.link!![entry.link!!.size - 1].href)
                .centerCrop()
                .resize(width / 2,width / 2)
                .into(itemView.picture)

        itemView.setOnClickListener { pictureClick.onPictureClick(entry, itemView.picture) }
    }

}