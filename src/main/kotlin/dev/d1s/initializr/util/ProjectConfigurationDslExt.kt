package dev.d1s.initializr.util

import dev.d1s.initializr.domain.api.JavaVersion
import dev.d1s.initializr.domain.api.ProjectLanguage
import dev.d1s.initializr.domain.api.ProjectPackaging
import dev.d1s.initializr.domain.api.ProjectType
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