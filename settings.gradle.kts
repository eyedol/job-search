// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
rootProject.name = "job-search"

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    // Skiko deps are here shrug
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    gradlePluginPortal()
  }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0" }

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    // Skiko deps are here shrug
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

include("app")
