// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  application
  id("plugins.conventions.spotless")
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.compose)
  alias(libs.plugins.kotlin.plugin.compose)
}

val jdk = libs.versions.jdk.get().toInt()
val mainClassStr = "com.addhen.job.search.AppKt"

tasks.withType<KotlinCompilationTask<*>>().configureEach {
  compilerOptions {
    progressiveMode.set(true)
    optIn.add("kotlin.ExperimentalStdlibApi")
    freeCompilerArgs.add("-Xexpect-actual-classes")

    if (this is KotlinJvmCompilerOptions) {
      freeCompilerArgs.add("-Xjsr305=strict")
      jvmTarget.set(JvmTarget.fromTarget(jdk.toString()))
    }
  }
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(jdk)) } }

application { mainClass.set("com.addhen.job.search.AppKt") }

kotlin {
  jvm()
  sourceSets {
    commonMain {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.material3)
        implementation(libs.selenium.java)
        implementation(compose.runtime)
        implementation(compose.material3)
        implementation(libs.compose.markdown)
        implementation(libs.compose.markdown.m3)
        implementation(libs.kotlinx.coroutines)
      }
    }
    jvmMain {
      dependencies {
        // To silence this stupid log https://www.slf4j.org/codes.html#StaticLoggerBinder
        implementation(libs.slf4jNop)
        implementation(compose.runtime)
        // https://github.com/ajalt/clikt/issues/438
        implementation(libs.clikt)
        implementation(compose.desktop.currentOs)
      }
    }
  }

  targets.withType<KotlinJvmTarget> {
    // Needed for 'application' plugin.
    withJava()
  }
}

// Fat jar configuration to run this as a standalone jar
// Configuration borrowed from https://stackoverflow.com/a/49284432/3323598
tasks.named<Jar>("jar") {
  manifest { attributes(mapOf("Main-Class" to mainClassStr)) }
  from(
    provider {
      configurations.compileClasspath
        .get()
        .filter { it.exists() }
        .map { if (it.isDirectory) it else zipTree(it) }
    }
  )
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
