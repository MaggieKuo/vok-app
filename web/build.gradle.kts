plugins {
    war
    id("org.gretty")
    id("com.vaadin")
    id("com.google.cloud.tools.appengine")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}
appengine {
    deploy {
        stopPreviousVersion = true
        promote = true
        version = "GCLOUD_CONFIG"
        projectId = "GCLOUD_CONFIG"
    }
}

dependencies {
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")
    providedCompile("com.google.appengine:appengine:+")
    implementation(kotlin("reflect"))
    implementation("eu.vaadinonkotlin:vok-framework-vokdb:${properties["vok_version"]}")
    implementation("eu.vaadinonkotlin:vok-security:${properties["vok_version"]}")
    // Vaadin
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}") {
        exclude(module = "fusion-endpoint") // exclude fusion: it brings tons of dependencies (including swagger)
    }
    implementation("com.vaadin:vaadin-bom:${properties["vaadin_version"]}")
    implementation("com.vaadin:vaadin-crud-flow:${properties["vaadin_version"]}")
    implementation("org.vaadin.crudui:crudui:6.2.0")

    providedCompile("javax.servlet:javax.servlet-api:4.0.1")

    implementation("com.zaxxer:HikariCP:5.0.1")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("org.slf4j:slf4j-api:1.7.36")

    // db
    implementation("org.flywaydb:flyway-core:8.5.12")
    implementation("com.h2database:h2:2.1.212")
    implementation("org.hibernate:hibernate-validator:6.2.3.Final")
    implementation("com.github.javafaker:javafaker:1.0.2")

    // REST
    implementation("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

}
vaadin {
    productionMode = true
    pnpmEnable = false  // workaround for https://github.com/vaadin/flow/issues/10571
}

