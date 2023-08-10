plugins {
    kotlin("multiplatform") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.gradle.maven-publish")
    id("dev.petuska.npm.publish") version "3.4.1"
}

group = "com.bpavuk"
version = "1.5.3"

repositories {
    mavenCentral()
}

val ktor_version = "2.3.2"

kotlin {
    jvm {
        jvmToolchain(8)
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    linuxX64()
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    js(IR) {
        moduleName = "catfactsSdk"
        browser {
            testTask(Action {
                useKarma {
                    useFirefox()
                }
            })
        }
        nodejs {
            testTask(Action {
                useMocha {
                    timeout = "20s"
                }
            })
        }
        useCommonJs()
        binaries.library()
    }
    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation("io.ktor:ktor-client-core:$ktor_version") // ядро, core
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")  // плагін ContentNegotiation
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")  // Serialization API
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
            }
        }

        val linuxX64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:$ktor_version") // двигун, engine
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktor_version") // двигун, engine
            }
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktor_version") // двигун, engine
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:$ktor_version") // двигун, engine
            }
        }
    }
}

publishing {
    repositories {
        maven {

        }
    }
}

npmPublish {
    registries {
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
            authToken.set(System.getenv("node_token"))
        }
    }
}
