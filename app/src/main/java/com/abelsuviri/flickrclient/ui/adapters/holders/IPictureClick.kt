package com.abelsuviri.flickrclient.ui.adapters.holders

import android.widget.ImageView
import com.abelsuviri.data.model.Entry

/**
 * @author Abel Suviri
 */

interface IPictureClick {
    fun onPictureClick(entry: Entry, picture: ImageView)
}