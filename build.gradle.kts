
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("com.google.cloud.tools:appengine-gradle-plugin:2.4.3")
    }
}

plugins {
    kotlin("jvm") version "1.7.0"
    id("org.gretty") version "3.0.6" apply(false)
    id("com.vaadin") version "23.1.3" apply(false)
}

defaultTasks("clean", "build")

allprojects {
    group = "com.example.vok"
    version = "1.0-SNAPSHOT"
    repositories {
        maven("https://maven.vaadin.com/vaadin-addons")
        mavenCentral()
        google()
    }
}

subprojects {
    apply { plugin("kotlin") }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            // to see the exceptions of failed tests in Travis-CI console.
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}
