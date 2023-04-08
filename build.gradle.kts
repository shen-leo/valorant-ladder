plugins {
//    kotlin("js") version "1.8.0"
    id ("org.jetbrains.kotlin.jvm") version ("1.8.0")
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
}

group = "me.leoshen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:1.6.4")
    implementation("io.ktor:ktor-client-json:1.6.7") // Replace with the latest version
    implementation("io.ktor:ktor-client-serialization:1.6.7") // Replace with the latest version
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("io.ktor:ktor-client-okhttp:1.6.7")
}


//kotlin {
//    js {
//        binaries.executable()
//        browser {
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
//    }
//}