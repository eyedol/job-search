// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
plugins {
  id("plugins.conventions.spotless")
  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.kotlin.plugin.compose) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.spotless) apply false
}
