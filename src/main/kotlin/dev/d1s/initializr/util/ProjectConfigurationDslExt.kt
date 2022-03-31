package dev.d1s.initializr.util

import dev.d1s.initializr.api.JavaVersion
import dev.d1s.initializr.api.ProjectLanguage
import dev.d1s.initializr.api.ProjectPackaging
import dev.d1s.initializr.api.ProjectType
import dev.d1s.initializr.dsl.ProjectConfigurationDsl

public fun ProjectConfigurationDsl.demo() {
    type = ProjectType.MAVEN
    language = ProjectLanguage.JAVA
    springBootVersion = "2.6.4"
    group = "com.example"
    artifact = "demo"
    name = "demo"
    description = "Demo project for Spring Boot"
    packageName = "com.example.demo"
    packaging = ProjectPackaging.JAR
    javaVersion = JavaVersion.VERSION_11
}