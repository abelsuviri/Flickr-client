package com.abelsuviri.data.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * @author Abel Suviri
 */

@Root(strict = false)
data class Feed @JvmOverloads constructor(
        @field:ElementList(name = "entry", required = false, inline = true) var entry: List<Entry>? = null
)