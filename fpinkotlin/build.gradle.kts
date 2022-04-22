plugins {
    kotlin("jvm") version "1.6.21"
}

group = "pro.reiss"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:1.0.1")
    implementation(kotlin("stdlib-jdk8"))
}
