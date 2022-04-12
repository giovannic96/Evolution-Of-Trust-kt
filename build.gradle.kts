import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "com.tw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.1-jre")

    testImplementation(kotlin("test"))
    testImplementation ("io.mockk:mockk:1.12.3")
    testImplementation ("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation ("net.javacrumbs.json-unit:json-unit-assertj:2.32.0")
    testImplementation ("org.assertj:assertj-core:3.22.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}