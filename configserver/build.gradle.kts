plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)

	alias(libs.plugins.springBoot)
	alias(libs.plugins.springBoot.dependencyManagement)
	alias(libs.plugins.jib)
}

val dockerImagePrefix = "rafalciesla"
val dockerImageName = "ryd-configserver"

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
	developmentOnly(libs.spring.devtools)

    // Spring Cloud
    implementation(libs.spring.cloud.configServer)

	// Observability
	implementation(libs.bundles.observability)
	runtimeOnly(libs.open.telemetry)

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

tasks.withType<Test> {
	useJUnitPlatform()
}