plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}
val id = "voidmill"

modSettings {
    modId(id)
    modName("Voidmill")
    description = "A helpful tool for making wood and wood related things"
    mixinFile("${id}.mixin.json")
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
}

base.archivesName = id
version = "1.0.5"
group = properties["maven_group"] as String
