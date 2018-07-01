package com.abelsuviri.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

/**
 * @author Abel Suviri
 */

@Root(strict = false)
data class Link @JvmOverloads constructor(
        @field:Attribute(name = "href", required = false) var href: String = ""
)