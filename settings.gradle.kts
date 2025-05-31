pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()

        maven("https://maven.fabricmc.net")
        maven("https://maven.teamvoided.org/releases")
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "Voidlib"

val modules = listOf(
    "headless",
    "voidcore",
    "voidmill",
    "vtags"
)
for (module in modules) {
    include(module)
    project(":$module").projectDir = file("modules/$module")
}


