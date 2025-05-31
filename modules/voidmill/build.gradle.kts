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
    modVersion = "1.0.6"
    modDescription = "A helpful tool for making wood and wood related things"
    mixinFile("${id}.mixin.json")
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(id, false)
    publishSources(true)
}
