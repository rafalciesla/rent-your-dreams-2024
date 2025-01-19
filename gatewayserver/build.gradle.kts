plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)

	alias(libs.plugins.springBoot)
	alias(libs.plugins.springBoot.dependencyManagement)
	alias(libs.plugins.jib)
}

val dockerImagePrefix = "rafalciesla"
val dockerImageName = "ryd-gatewayserver"

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
	implementation(libs.spring.cloud.configClient)
	implementation(libs.spring.cloud.eurekaClient)
	implementation(libs.spring.cloud.gateway)

	// Security
	implementation(libs.spring.security)
	implementation(libs.spring.security.oauth2.resourceServer)
	implementation(libs.spring.security.oauth2.jose)

	// Observability
	implementation(libs.bundles.observability)
	runtimeOnly(libs.open.telemetry)

	// Resiliency
	implementation(libs.resilience4j.circuitBreaker)
	implementation(libs.redis.reactive)

	// Code generation
	compileOnly(libs.lombok.compile)
	annotationProcessor(libs.lombok.annotation)

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
