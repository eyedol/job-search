// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import com.addhen.job.search.data.model.JobPost
import com.addhen.job.search.data.model.JobSearchConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.LocalDateTime

class LinkedInJobSearch(
  private val webDriver: WebDriver,
  private val linkedUrl: String = "https://www.linkedin.com",
): JobSearch {

  override suspend fun authenticate(email: String, password: String) = withContext(Dispatchers.IO){
    driver(webDriver) {
      get("$linkedUrl/login")
      elementById("username") { sendKeys(email) }
      elementById("password") { sendKeys(password) }
      elementByClass("button[type='submit']") { click() }
      wait(10) { urlContains("feed") { println("Logged in successfully") } }
    }
  }

  override suspend fun search(config: JobSearchConfig): List<JobPost> = withContext(Dispatchers.IO) {
    listOf(
      JobPost(
      "title",
      "company",
      "location",
        LocalDateTime.now(),
        "description", "url"
      )
    )
  }
}

inline fun SearchContext.elementById(id: String, init: WebElement.() -> Unit) =
  findElement(By.id(id)).init()

inline fun <T> SearchContext.elementByClass(className: String, init: WebElement.() -> T): T =
  findElement(By.className(className)).init()

inline fun WebDriverWait.urlContains(fraction: String, init: Boolean.() -> Unit) {
  until(ExpectedConditions.urlContains(fraction)).init()
}
