plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.iridium)
    alias(libs.plugins.iridium.publish)
}
val username = "vDev"
val uuid: String? = null


allprojects {
    repositories {
        mavenLocal()
        maven("https://teamvoided.org/releases") { content { includeGroup("org.teamvoided") } }
        maven("https://teamvoided.org/snapshots") { content { includeGroup("org.teamvoided") } }
        maven("https://maven.fzzyhmstrs.me/") { name = "FzzyMaven"; content { includeGroup("me.fzzyhmstrs") } }
        maven("https://maven.terraformersmc.com/") {
            name = "Terraformers"
            content {
                includeGroup("com.terraformersmc")
                includeGroup("dev.emi")
            }
        }
        maven("https://api.modrinth.com/maven") { content { includeGroup("maven.modrinth") } }
        mavenCentral()
    }

    loom {
        runs {
            named("client") {
                programArgs("--username", username)
                uuid?.let { programArgs("--uuid", uuid) }
            }

            create("TestWorld") {
                client()
                ideConfigGenerated(true)
                runDir("run")
                programArgs("--quickPlaySingleplayer", "test", "--username", username)
                uuid?.let { programArgs("--uuid", uuid) }
            }
            runConfigs.forEach { it.ideConfigGenerated(false) }
        }
    }
}

dependencies {
    // Dependencies
    modImplementation(libs.fzzy.config)
    // QoL
    modImplementation(libs.modmenu)
    modCompileOnly("${libs.emi.get()}:api")
    modLocalRuntime(libs.emi)
    // Testing
    modImplementation(libs.creative.works)
    modCompileOnly(libs.imguimc)
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), false)
    publishSources(true)
}
