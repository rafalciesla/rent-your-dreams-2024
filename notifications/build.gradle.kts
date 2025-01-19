plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)

	alias(libs.plugins.springBoot)
	alias(libs.plugins.springBoot.dependencyManagement)
	alias(libs.plugins.jib)
}

val dockerImagePrefix = "rafalciesla"
val dockerImageName = "ryd-notifications"

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
	implementation(libs.spring.cloud.stream)
	implementation(libs.spring.cloud.stream.rabbit)

	// Observability
	implementation(libs.bundles.observability)
	runtimeOnly(libs.open.telemetry)

	// Tests
	testImplementation(libs.bundles.test)
	testRuntimeOnly(libs.junit.launcher)

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
