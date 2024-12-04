plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("kapt")
    id("groovy")
    id("org.springframework.boot") version "3.3.6"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.google.cloud.tools.jib") version "3.4.4"
}

group = "pl.ciesla.ryd"
version = "0.0.2-SNAPSHOT"
val imagePrefix = "rafalciesla"
val dockerImageName = "ryd-cars"

jib {
    from {
        image = "eclipse-temurin:21.0.5_11-jre-alpine"
    }
    to {
        image = "${imagePrefix}/${dockerImageName}"
        tags = setOf("$version", "latest")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Core / Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.flywaydb:flyway-core")

    // Code generation
    kapt("org.mapstruct:mapstruct-processor:1.6.2")
    implementation("org.mapstruct:mapstruct:1.6.2")

    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    // Internal libraries
    implementation(project(":lib"))

    // Tests
    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
    testImplementation("org.apache.groovy:groovy:4.0.24")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
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