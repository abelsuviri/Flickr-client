package com.abelsuviri.flickrclient.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abelsuviri.data.model.Entry
import com.abelsuviri.flickrclient.R
import com.abelsuviri.flickrclient.ui.adapters.holders.IPictureClick
import com.abelsuviri.flickrclient.ui.adapters.holders.PictureViewHolder

/**
 * @author Abel Suviri
 */

class PicturesAdapter constructor(private val entries: List<Entry>, private val pictureClick: IPictureClick): RecyclerView.Adapter<PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return PictureViewHolder(itemView, pictureClick)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}