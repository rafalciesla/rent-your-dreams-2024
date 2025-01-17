plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.noarg)
    alias(libs.plugins.kotlin.jpa)

    alias(libs.plugins.springBoot)
    alias(libs.plugins.springBoot.dependencyManagement)
    alias(libs.plugins.jib)
    alias(libs.plugins.groovy)
}

val dockerImagePrefix = "rafalciesla"
val dockerImageName = "ryd-cars"

jib {
    from {
        image = "eclipse-temurin:21.0.5_11-jre-alpine"
    }
    to {
        image = "${dockerImagePrefix}/${dockerImageName}"
        tags = setOf("$version", "latest")
    }
}

dependencies {
    // Kotlin
    implementation(libs.bundles.kotlin)

    // Spring Core / Web
    implementation(libs.bundles.spring.web)
    developmentOnly(libs.spring.devtools)

    // Spring Cloud
    implementation(libs.bundles.spring.cloud)

    // Observability
    implementation(libs.bundles.observability)
    runtimeOnly(libs.open.telemetry)

    // DB
    implementation(libs.bundles.postgres.db)
    runtimeOnly(libs.postgres.runtime)

    // Code generation
    compileOnly(libs.lombok.compile)
    annotationProcessor(libs.lombok.annotation)

    // Internal libraries
    implementation(project(":lib"))

    // Tests
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
    testImplementation("org.apache.groovy:groovy:4.0.24")
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

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.versions.spring.cloud.get()}")
    }
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

tasks.test {
    useJUnitPlatform()
}