pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://maven.fabricmc.net")
        maven("https://maven.teamvoided.org/releases")
    }
}

rootProject.name = "Voidlib2"

include("exampleModule")
project(":exampleModule").projectDir = file("modules/exampleModule")
