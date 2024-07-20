// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import com.addhen.job.search.data.model.ChromeDriverSettings

class JobSearch2() {

  fun search() {
    val chromeDriverSettings = ChromeDriverSettings(pathToDriver = "/opt/homebrew/bin/chromedriver")
    chromeDriver(chromeDriverSettings) {
      LinkedInJobSearch(webDriver = this).login("email", "password")
    }
  }
}
