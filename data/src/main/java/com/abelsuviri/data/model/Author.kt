package com.abelsuviri.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * @author Abel Suviri
 */

@Root(strict = false)
data class Author @JvmOverloads constructor(
        @field:Element(name = "name", required = false) var name: String = ""
)