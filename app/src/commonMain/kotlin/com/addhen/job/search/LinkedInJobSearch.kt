// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import com.addhen.job.search.data.model.JobPost
import com.addhen.job.search.data.model.JobSearchConfig
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequest
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import io.ktor.http.encodeURLQueryComponent
import java.time.LocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LinkedInJobSearchWithKSoup(
  private val ksoup: Ksoup = Ksoup,
  private val linkedInBaseUrl: String = "https://www.linkedin.com/jobs/search/?",
) : JobSearch {

  override suspend fun authenticate(email: String, password: String) {
    TODO("Not yet implemented")
  }

  override suspend fun search(config: JobSearchConfig): List<JobPost> =
    withContext(Dispatchers.IO) {
      val keywordsParam = config.jobTitles.joinToString(" OR ").encodeURLQueryComponent()
      val locationsParam = config.locations.joinToString(" OR ").encodeURLQueryComponent()

      val url =
        "${linkedInBaseUrl}keywords=$keywordsParam&location=$locationsParam&f_TPR=r86400&f_WT=2,3&sortBy=DD"
      println(url)
      val doc: Document = ksoup.parseGetRequest(url)

      val jobListings = doc.select("div.base-card")
      println("Found ${jobListings.size} job listings on the page.")
      val results = mutableListOf<JobPost>()

      ksoup.get(url) {
        elements("div.base-card") {
          forEachElement("div.base-card") {
            val title =
              select("h3.base-search-card__title") {
                text().takeIf { it.isNotEmpty() } ?: "Title not found"
              }
            val company =
              select("h4.base-search-card__subtitle") {
                text().takeIf { it.isNotEmpty() } ?: "Company not found"
              }
            val location =
              select("span.job-search-card__location") {
                text().takeIf { it.isNotEmpty() } ?: "Location not found"
              }
            val link =
              select("a.base-card__full-link") {
                attr("href").takeIf { it.isNotEmpty() } ?: "Link not found"
              }
            val timePosted =
              select("time.job-search-card__listdate") {
                attr("datetime").takeIf { it.isNotEmpty() } ?: LocalDate.now().toString()
              }
            val moment =
              select("time.job-search-card__listdate") {
                text().takeIf { it.isNotEmpty() } ?: "Moment not found"
              }

            val jobPost =
              JobPost(title, company, location, LocalDate.parse(timePosted), moment, link)

            results.add(jobPost)
          }
        }
      }
      results.toList()
    }

  companion object {
    fun create(): JobSearch = LinkedInJobSearchWithKSoup()
  }
}

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
