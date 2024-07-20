// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.system.exitProcess

class App : CliktCommand() {
  override fun run() {
    val iTerm = Terminal(AnsiLevel.TRUECOLOR, interactive = true)
    iTerm.print(JobSearch2().search())
    exitProcess(0)
  }
}

fun main(argv: Array<String>) {
  App().main(argv)
}
