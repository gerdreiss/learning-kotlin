import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "pro.reiss"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.arrow-kt:arrow-stack:1.1.2"))
    implementation("io.arrow-kt:arrow-core")
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-property:5.2.3")
    testImplementation("io.kotest:kotest-runner-junit5:5.2.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("fpinkotlin.MainKt")
}