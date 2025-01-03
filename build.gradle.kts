plugins {
    kotlin("jvm") version "2.1.0"
}

group = "pl.ciesla.ryd"
version = "0.0.2-SNAPSHOT"

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