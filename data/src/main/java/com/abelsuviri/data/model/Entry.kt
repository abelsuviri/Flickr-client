package com.abelsuviri.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * @author Abel Suviri
 */

@Root(strict = false)
data class Entry @JvmOverloads constructor(
        @field:Element(name = "title", required = false) var title: String = "",
        @field:Element(name = "published", required = false) var published: String = "",
        @field:ElementList(entry = "link", required = false, inline = true) var link: List<Link>? = null,
        @field:Element(name = "author", required = false) var author: Author? = null
)