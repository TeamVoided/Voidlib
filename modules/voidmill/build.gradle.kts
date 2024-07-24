plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}

modSettings {
    modId("voidmill")
    modName("VoidLib2: Example Module")
    mixinFile("voidmill.mixin.json")
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
    publication(modSettings.modId(), isSnapshot = true)
}

base.archivesName = "voidlib2-voidmill"
version = "1.0.1"
group = properties["maven_group"] as String
