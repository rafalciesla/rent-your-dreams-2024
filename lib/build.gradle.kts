import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.noarg)
    alias(libs.plugins.kotlin.jpa)

    alias(libs.plugins.springBoot)
    alias(libs.plugins.springBoot.dependencyManagement)
}

dependencies {

    // Kotlin
    implementation(libs.bundles.kotlin)

    // Spring Core / Web
    implementation(libs.bundles.spring.web)

    // DB
    implementation(libs.spring.data)

}

kotlin {
    jvmToolchain(JavaLanguageVersion.of(libs.versions.java.get()).asInt())
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

repositories {
    mavenCentral()
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.named<BootBuildImage>("bootBuildImage") {
    enabled = false
}