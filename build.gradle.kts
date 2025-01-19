plugins {
    alias(libs.plugins.kotlin.jvm)
}

allprojects {
    group = "pl.ciesla.ryd"
    version = "0.0.4-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}