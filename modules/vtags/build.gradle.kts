plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("iridium.mod.build-script")
    id("iridium.project.publish-script")
}
val id = "vtags"
base.archivesName = id

modSettings {
    modId(id)
    modName("vTags")
    modVersion = "0.1.0"
    modDescription = "Tags that should be in vanilla or at least in #c"

    entrypoint("fabric-datagen", "org.teamvoided.vtags.VTagsData")
}

loom {
    runs {
        create("TagGen") {
            client()
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${id}")
            runDir("build/datagen")
        }
    }
}

dependencies {
    modImplementation(project(":voidcore"))
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), isSnapshot = false)
}

