import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
}

group = "com.bpavuk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktor_version = "2.3.2"

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version") // ядро, core
    implementation("io.ktor:ktor-client-cio:$ktor_version") // двигун, engine
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")  // плагін ContentNegotiation
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")  // Serialization API
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
