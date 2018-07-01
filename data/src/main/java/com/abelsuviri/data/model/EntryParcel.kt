package com.abelsuviri.data.model

import android.os.Parcelable
import com.google.auto.value.AutoValue

/**
 * @author Abel Suviri
 */

@AutoValue
abstract class EntryParcel: Parcelable {
    abstract val title: String
    abstract val link: String
    abstract val author: String
    abstract val publishDate: String

    companion object {
        fun create(title: String, link: String, author: String, publishDate: String): EntryParcel {
            return AutoValue_EntryParcel(title, link, author, publishDate)
        }
    }
}