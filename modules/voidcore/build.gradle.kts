plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}
val id = "voidcore"
base.archivesName = id

modSettings {
    modId(id)
    modName("Void Core")
    modVersion = "0.1.0"
    modDescription = "The core of void lib"

    entrypoint("main", "org.teamvoided.voidcore.VoidCore::init")
    entrypoint("client", "org.teamvoided.voidcore.VoidCore::initClient")
    mixinFile("${id}.mixin.json")
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
}

