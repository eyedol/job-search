package com.addhen.job.search

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequest
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements

suspend fun Ksoup.get(url: String, init: Document.() -> Unit) {
    parseGetRequest(url).init()
}

fun Document.elements(className: String, init: Elements.() -> Unit) {
    return select(className).init()
}

fun Elements.forEachElement(className: String, init: Element.() -> Unit) {
    select(className).forEach(init)
}

inline fun <T> Element.select(className: String, init: Elements.() -> T): T {
    return select(className).init()
}