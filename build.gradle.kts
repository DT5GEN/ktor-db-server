val kotlinVersion: String by project
val logback_version: String by project
val exposed_version: String by project
val postgres_version: String by project
val hikari_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.0.21"
    id("io.ktor.plugin") version "3.0.0"
    kotlin("plugin.serialization") version "1.9.0"
}

group = "com.dt5gen"
version = "0.0.1"

application {
    mainClass.set("com.dt5gen.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-sessions-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-gson-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-sessions:2.3.0")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("io.ktor:ktor-auth:1.6.8")
    implementation("io.ktor:ktor-auth-jwt:1.6.8")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
    //  implementation("org.postgresql:postgresql:42.7.4")


    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
