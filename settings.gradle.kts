pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://maven.fabricmc.net")
        maven("https://maven.teamvoided.org/releases")
    }
}

rootProject.name = "Voidlib"

include("voidmill")
project(":voidmill").projectDir = file("modules/voidmill")

include("voidcore")
project(":voidcore").projectDir = file("modules/voidcore")
