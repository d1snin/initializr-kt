[![](https://jitpack.io/v/d1snin/initializr-kt.svg)](https://jitpack.io/#d1snin/initializr-kt)

# initializr-kt

Asynchronous Spring Initializr API wrapper for Kotlin/JVM.\
This library provides the simplest DSL for initializing Spring Boot projects using Spring Initializr.

### Installation

```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("dev.d1s:initializr-kt:$initializrKtVersion")
}
```

### Example usage

```kotlin
private val initializr = initializr()

suspend fun main() {
    val zip = initializr.createProject {
        packageName = "dev.d1s.test"
        group = "dev.d1s"
        artifact = "test"
        name = "test"
        language = ProjectLanguage.KOTLIN
        type = ProjectType.GRADLE
        javaVersion = JavaVersion.VERSION_11
        packaging = ProjectPackaging.JAR
        springBootVersion = "3.0.0-M2" // latest
        dependencies += setOf(
            ProjectDependency.SPRING_REACTIVE_WEB,
            ProjectDependency.SPRING_DATA_JPA
        )
    }

    withContext(Dispatchers.IO) {
        Files.write(Paths.get("./test.zip"), zip, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }
}
```

Note that the initialization will fail if **any** of the properties is not satisfied. To avoid this, you can describe
the default project configuration.

### Default project configuration

Configure the default project configuration:

```kotlin
private val initializr = initializr()

suspend fun main() {

    // async
    initializr.setDefaultProjectConfiguration {
        language = ProjectLanguage.KOTLIN
        type = ProjectType.GRADLE
        javaVersion = JavaVersion.VERSION_11
        packaging = ProjectPackaging.JAR
        springBootVersion = "3.0.0-M1" // latest
    }
    
    // you will not be required to satisfy preconfigured properties:
    val zip = initializr.createProject {
        packageName = "dev.d1s.anotherTest"
        group = "dev.d1s"
        artifact = "another-test"
        name = "another-test"
        dependencies += setOf(
            ProjectDependency.SPRING_REACTIVE_WEB,
            ProjectDependency.SPRING_DATA_JPA
        )
    }
}
```

