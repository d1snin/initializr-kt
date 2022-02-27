import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "uno.d1s"
version = "0.2.0-alpha.0"

repositories {
    mavenCentral()
}

extra["ktorVersion"] = "2.0.0-beta-1"
extra["jacksonVersion"] = "2.13.1"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-cio-jvm:${property("ktorVersion")}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${property("jacksonVersion")}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${property("jacksonVersion")}")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events.addAll(
            listOf(
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
            )
        )
    }
}

tasks.withType<KotlinCompile> {
    targetCompatibility = "11"
}

tasks.withType<Jar> {
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("initializr-kt") {
            from(components["java"])
        }
    }
}

kotlin {
    explicitApi = ExplicitApiMode.Warning
}
