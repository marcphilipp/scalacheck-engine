plugins {
    scala
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.12.10")
    testImplementation("org.scalacheck:scalacheck_2.12:1.14.3")
    testRuntimeOnly(project(":engine"))
}

tasks.test {
    useJUnitPlatform()
}
