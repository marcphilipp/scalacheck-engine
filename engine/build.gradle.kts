plugins {
    scala
    `java-library`
    `maven-publish`
}

group = "org.scalacheck"
version = "0.1.0"

val scalaVersion: String? by project
val scalaBinaryVersion: String? by project

repositories {
    mavenCentral()
}

dependencies {
    api(platform("org.junit:junit-bom:5.6.0"))
    api("org.junit.platform:junit-platform-engine")
    implementation("org.scalacheck:scalacheck_${scalaBinaryVersion}:1.14.3")
    implementation("org.scala-lang:scala-library:${scalaVersion}")
    implementation("org.scala-lang.modules:scala-java8-compat_${scalaBinaryVersion}:0.9.0")
    testImplementation("junit:junit:4.13")
    testImplementation("org.scalatest:scalatest_${scalaBinaryVersion}:3.0.8")
    testImplementation("org.junit.platform:junit-platform-testkit")
    testRuntimeOnly("org.scala-lang.modules:scala-xml_${scalaBinaryVersion}:1.2.0")
}

java {
    withJavadocJar()
    withSourcesJar()
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "scalacheck-engine_${scalaBinaryVersion}"
            from(components["java"])
        }
    }
}
