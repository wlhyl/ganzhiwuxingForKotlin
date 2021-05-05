import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
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
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
//    testCompile(group: "junit":junit:4.12")
}


//tasks.withType<KotlinCompile>() {
//    kotlinOptions.jvmTarget = "1.8"
//}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.6"
    }
}

//compileTestKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//}


tasks.test {
    useJUnitPlatform()
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

//打包源码
//task sourceJar(type: Jar) {
//    from sourceSets.main.allSource
//}
//
//publishing {
//    publications {
//        maven(MavenPublication) {
//            //指定group/artifact/version信息，可以不填。默认使用项目group/name/version作为groupId/artifactId/version
//            groupId project.group
//            artifactId project.name
//            version project.version
//            //如果是war包填写components.web，如果是jar包填写components.java
//            from components.java
//
//            //配置上传源码
//            artifact sourceJar {
//                classifier "sources"
//            }
//
//        }
//    }
//    repositories {
//        maven {
//            //指定要上传的maven私服仓库
////            url = "http://172.168.1.8/repository/maven-releases/"
//            url = "http://172.168.1.8/repository/maven-snapshots/"
//            //认证用户和密码
//            credentials {
//                username 'admin'
//                password 'admin123'
//            }
//        }
//    }
//}