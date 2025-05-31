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
    modDescription = "Got head?"


    entrypoint("main", "org.teamvoided.headless.Headless::init")
    entrypoint("client", "org.teamvoided.headless.HeadlessClient::init")
//    entrypoint("fabric-datagen", "org.teamvoided.headless.data.gen.HeadlessData")
    mixinFile("${modId()}.client.mixins.json")
    mixinFile("${modId()}.mixins.json")
}

loom {
    splitEnvironmentSourceSets()
  /*  runs {
        create("TagGen") {
            client()
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${id}")
            runDir("build/datagen")
        }
    }*/
}

dependencies {
    modImplementation(project(":voidcore"))
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
}

