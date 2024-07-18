package com.addhen.job.search

import com.addhen.job.search.data.model.ChromeDriverSettings

class JobSearch() {

    fun search() {
        val chromeDriverSettings = ChromeDriverSettings(pathToDriver = "/opt/homebrew/bin/chromedriver")
        chromeDriver(chromeDriverSettings) {
            LinkedInJobSearch(
                webDriver = this
            ).login("email", "password")
        }
    }
}