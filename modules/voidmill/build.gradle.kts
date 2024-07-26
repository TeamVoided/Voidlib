plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}

modSettings {
    modId("voidmill")
    modName("Voidmill")
    description = "A helpful tool for making wood and wood related things"
    mixinFile("voidmill.mixin.json")
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
    publication(modSettings.modId(), isSnapshot = true)
}

base.archivesName = "voidmill"
version = "1.0.4"
group = properties["maven_group"] as String
