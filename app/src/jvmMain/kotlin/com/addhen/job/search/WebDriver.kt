package com.addhen.job.search

import com.addhen.job.search.data.model.ChromeDriverSettings
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

inline fun <T : WebDriver> driver(driver: T, init: T.() -> Unit) {
    try {
        driver.init()
    } finally {
        driver.close()
    }
}

fun WebDriver.wait(
    timeout: Long,
    sleepTimeout: Long = 500,
    init: WebDriverWait.() -> Unit
) {
    WebDriverWait(this, Duration.ofMillis(timeout), Duration.ofMillis(sleepTimeout)).init()
}

fun chromeDriver2(init: WebDriver.() -> Unit) = driver(ChromeDriver(), init)

fun chromeDriver(
    settings: ChromeDriverSettings,
    init: ChromeDriver.() -> Unit
) = driver(ChromeDriver(settings.driverOptions), init)