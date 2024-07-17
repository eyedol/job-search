// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package plugins.conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugins.configureSpotless

class SpotlessConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) { configureSpotless() }
  }
}
