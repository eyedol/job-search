// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search.data.model

import org.openqa.selenium.chrome.ChromeOptions

abstract class DriverOptions(driverPath: String) {
  init {
    require(driverPath.isNotEmpty()) { "Path to driver cannot be empty" }
    //System.setProperty("webdriver.chrome.driver", driverPath)
  }
}

data class ChromeDriverSettings(
  val pathToDriver: String,
  val driverOptions: ChromeOptions = ChromeOptions(),
) : DriverOptions(pathToDriver)
