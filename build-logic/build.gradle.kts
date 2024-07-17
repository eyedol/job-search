plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("spotlessConvention") {
            id = "plugins.conventions.spotless"
            implementationClass = "plugins.conventions.SpotlessConventionPlugin"
        }
    }
}

spotless {
    kotlin {
      target("src/**/*.kt")
      ktfmt("0.47").googleStyle()
      licenseHeaderFile(rootProject.file("../spotless/copyright.txt"))
    }
    kotlinGradle {
      target("*.kts")
      ktfmt("0.47").googleStyle()
      licenseHeaderFile(rootProject.file("../spotless/copyright.txt"), "(^(?![\\/ ]\\**).*$)")
    }
  }
