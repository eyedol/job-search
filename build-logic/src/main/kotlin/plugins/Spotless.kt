// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package plugins

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
  with(pluginManager) { apply("com.diffplug.spotless") }

  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("${layout.buildDirectory}/**/*.kt")
      targetExclude("bin/**/*.kt")
      ktfmt("0.47").googleStyle()
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
      licenseHeaderFile(project.rootProject.file("spotless/copyright.txt"))
    }

    kotlinGradle {
      target("*.kts")
      ktfmt("0.47").googleStyle()
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
      licenseHeaderFile(rootProject.file("spotless/copyright.txt"), "(^(?![\\/ ]\\**).*$)")
    }

    format("kts") {
      target("**/*.kts")
      targetExclude("**/build/**/*.kts")
    }
  }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) =
  extensions.configure<SpotlessExtension>(action)
