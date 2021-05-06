import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.0"
//    kotlin("jvm") version "1.4.32"
//    kotlin("plugin.spring") version "1.4.32"
    id("maven-publish")
}

group ="pub.teanote"
//version '1.0'
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.6"
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
    repositories {
        val snapshotsRepoUrl = "http://172.168.1.8:8081/repository/maven-snapshots/"
        val releasesRepoUrl = "http://172.168.1.8:8081/repository/maven-releases/"
        maven {
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials{
                username = "admin"
                password = "admin"
            }
        }
    }
}