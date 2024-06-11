plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    id("org.teamvoided.iridium") version "3.3.0"
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    //minecraft(libs.minecraft)
    //mappings(variantOf(libs.quilt.mappings) { classifier("intermediary-v2") })
}
