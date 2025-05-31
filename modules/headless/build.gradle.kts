plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}
val id = "headless"
base.archivesName = id

modSettings {
    modId(id)
    modName("Headless")
    modVersion = "1.0.0"
    modDescription = "Yeah, I got head!"

    entrypoint("main", "org.teamvoided.headless.Headless::init")
    entrypoint("client", "org.teamvoided.headless.HeadlessClient::init")

    mixinFile("${modId()}.client.mixins.json")
    mixinFile("${modId()}.mixins.json")
}

loom {
    splitEnvironmentSourceSets()
}

dependencies {
    modImplementation(project(":voidcore"))
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
}

