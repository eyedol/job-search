// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.addhen.job.search.data.model.ChromeDriverSettings
import org.openqa.selenium.chrome.ChromeDriver

fun main() =
  singleWindowApplication(
    title = "Job Search App",
    state =
      WindowState(
        width = 1000.dp,
        height = 800.dp,
        position = WindowPosition.Aligned(Alignment.Center),
      ),
  ) {

    val settings = ChromeDriverSettings(pathToDriver = "/opt/homebrew/bin/chromedriver")
    val webDriver = ChromeDriver(settings.driverOptions)
    val jobSearch = LinkedInJobSearch(webDriver = webDriver)
    JobSearchCompose(jobSearch)
  }
