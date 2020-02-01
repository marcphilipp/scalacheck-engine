plugins {
    scala
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    api(platform("org.junit:junit-bom:5.6.0"))
    api("org.junit.platform:junit-platform-engine")
    implementation("org.scalacheck:scalacheck_2.12:1.14.3")
    implementation("org.scala-lang:scala-library:2.12.10")
    implementation("org.scala-lang.modules:scala-java8-compat_2.12:0.9.0")
    testImplementation("junit:junit:4.13")
    testImplementation("org.scalatest:scalatest_2.12:3.0.8")
    testImplementation("org.junit.platform:junit-platform-testkit")
    testRuntimeOnly("org.scala-lang.modules:scala-xml_2.12:1.2.0")
}
