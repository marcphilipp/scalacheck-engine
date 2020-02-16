# scalacheck-engine

Prototype of a [JUnit Platform Test Engine](https://junit.org/junit5/docs/current/user-guide/#launcher-api-engines-custom) for executing [ScalaCheck](https://www.scalacheck.org/) property-based tests on the JVM.

## Demos

In order to use the engine it has to be published to the local Maven repository:

`./gradlew pTML`

This will publish the engine unter the GAV coordinates `org.scalacheck:scalacheck-engine:0.1`.

### Gradle

In the `demo/gradle` folder run `./gradlew test`.
This will execute `com.example.StringSpecification` using the [built-in test task](https://docs.gradle.org/current/userguide/java_testing.html) and result in den following build log:

```
> Task :test FAILED

String > String.concatenate FAILED
    org.opentest4j.AssertionFailedError at PropertyTestDescriptor.scala:33

3 tests completed, 1 failed
```

A test report will be written to `demo/gradle/build/reports/tests/test/index.html` and a build scan will be created containing the [succeeding and failing tests](https://scans.gradle.com/s/wmfmadfvqhkvo/tests/by-project).

### Maven

In the `demo/maven` folder run `./mvnw clean test`.
This will execute `com.example.StringSpecification` using [Apache Maven Surefire](https://maven.apache.org/surefire/maven-surefire-plugin/) and result in den following build log:

```
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ scalacheck-maven-demo ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running String
failing seed for String.concatenate is __KRA-X-eAqdcaOIXXGj3yZ3KiENdyCcIpPSSmz0NrM=
[ERROR] Tests run: 3, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.417 s <<< FAILURE! - in String
[ERROR] String.concatenate  Time elapsed: 0.037 s  <<< FAILURE!
org.opentest4j.AssertionFailedError:
Falsified after 0 passed tests.
> ARG_0: ""
> ARG_1: ""

[INFO]
[INFO] Results:
[INFO]
[ERROR] Failures:
[ERROR]   StringSpecification$ Falsified after 0 passed tests.
> ARG_0: ""
> ARG_1: ""
[INFO]
[ERROR] Tests run: 3, Failures: 1, Errors: 0, Skipped: 0
```

A test report will be written to `demo/maven/target/surefire-reports/TEST-String.xml` and a build scan will be created containing the [succeeding and failing tests](https://scans.gradle.com/s/rjhvgolpppyam/tests/by-project).
