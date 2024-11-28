plugins {
	kotlin("jvm") version "2.0.21"
	kotlin("plugin.spring") version "2.0.21"
    id("org.springframework.boot") version "3.3.6"
	id("io.spring.dependency-management") version "1.1.6"
    id("com.google.cloud.tools.jib") version "3.4.4"
}

group = "pl.ciesla"
version = "0.0.1-SNAPSHOT"
val imagePrefix = "rafalciesla"
val dockerImageName = "ryd-configserver"

jib {
    from {
        image = "eclipse-temurin:21.0.5_11-jre-alpine"
    }
    to {
        image = "${imagePrefix}/${dockerImageName}:${version}"
        image = "${imagePrefix}/${dockerImageName}:latest"
    }
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {

    // Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Core / Web
    implementation("org.springframework.boot:spring-boot-starter-actuator")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-config-server")

}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
    jvmToolchain(21)
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}