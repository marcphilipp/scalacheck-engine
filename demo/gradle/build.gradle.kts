plugins {
    scala
}

repositories {
    mavenLocal {
        content {
            includeModule("org.scalacheck", "scalacheck-engine")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.12.10")
    testImplementation("org.scalacheck:scalacheck_2.12:1.14.3")
    testRuntimeOnly("org.scalacheck:scalacheck-engine:0.1")
}

tasks.test {
    useJUnitPlatform()
}
