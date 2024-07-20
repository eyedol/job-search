// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import com.addhen.job.search.data.model.JobSearchConfig
import com.addhen.job.search.data.model.markdown
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.markdown.Markdown
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Spinner
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

class CliApp : CliktCommand() {
  override fun run() {
    val iTerm = Terminal(AnsiLevel.TRUECOLOR, interactive = true)

    val animation =
      iTerm.progressAnimation {
        spinner(Spinner.Dots(TextColors.brightYellow))
        text("Loading...")
        progressBar()
      }
    animation.start()
    val searchResults = runBlocking { LinkedInJobSearchWithKSoup.create().search(JobSearchConfig()).markdown() }
    animation.stop()

    iTerm.print(Markdown(searchResults, hyperlinks = true))
  }
}

fun main(argv: Array<String>) {
  
  val cli = CliApp()
  try {
      cli.parse(argv)
  } catch (e: CliktError) {
      cli.echoFormattedHelp(e)
      exitProcess(e.statusCode)
  } finally {
      exitProcess(0)
  }
}
