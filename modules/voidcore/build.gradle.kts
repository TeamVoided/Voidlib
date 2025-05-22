plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}
val id = "voidcore"

modSettings {
    modId(id)
    modName("Void Core")
    description = "A helpful tool for making wood and wood related things"

    entrypoint("main", "org.teamvoided.voidcore.VoidCore::init")
    entrypoint("client", "org.teamvoided.voidcore.VoidCore::initClient")
    mixinFile("${id}.mixin.json")
}

loom{
    runs {
        create("TestWorld") {
            client()
            ideConfigGenerated(true)
            runDir("run")
            programArgs("--quickPlaySingleplayer", "test", "--username", "Endoside", "--uuid", "a5fc6689-7d19-4c39-a04e-95e4ec460298")

        }
    }
}
publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
}


base.archivesName = id
version = "1.0.0"
group = properties["maven_group"] as String
