plugins {
    scala
}

val scalaVersion: String? by project
val scalaBinaryVersion: String? by project

repositories {
    mavenLocal {
        content {
            includeModule("org.scalacheck", "scalacheck-engine_${scalaBinaryVersion}")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:${scalaVersion}")
    testImplementation("org.scalacheck:scalacheck_${scalaBinaryVersion}:1.14.3")
    testRuntimeOnly("org.scalacheck:scalacheck-engine_${scalaBinaryVersion}:0.1.0")
}

tasks.test {
    useJUnitPlatform()
}
