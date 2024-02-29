import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.sonarqube.gradle.SonarExtension
import org.sonarqube.gradle.SonarTask

plugins {
    alias(libs.plugins.python.plugin)// apply false
    id("jacoco-report-aggregation")
    id("java-library")
    alias(libs.plugins.kotlin.jvm)// apply false
    alias(libs.plugins.sonarqube.plugin)
}

// project meta data
group = "de.havox_design.aoc"
version = "2024.2.2"

dependencies {
    rootProject.subprojects.forEach { subproject ->
        jacocoAggregation(subproject)
    }
}

configure<SonarExtension> {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "de.havox_design.aoc:advent_of_code")
        property("sonar.organization", "havox")
        property("sonar.sourceEncoding", "UTF-8")
    }
}

if (tasks.findByName("check") == null) {
    tasks.register("check") {
        group = "verification"
        description = "Collector task for check tasks..."
    }
}

tasks.named("check") {
    dependsOn(tasks.named("testCodeCoverageReport"))
}

// Switch to gradle "all" distribution.
tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "8.6"
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

rootProject.allprojects.forEach { currentProject ->
    currentProject.apply(plugin = "jacoco")
    currentProject.apply(plugin = "java-library")

    currentProject.repositories {
        mavenCentral()
    }

    currentProject.dependencies {
        testImplementation(libs.hamcrest)
        testImplementation(libs.junit.jupiter)
    }

    currentProject.jacoco {
        toolVersion = "0.8.11"
    }

    currentProject.tasks.named<JacocoReport>("jacocoTestReport") {
        reports {
            html.required.set(true)
            xml.required.set(true)
        }

        rootProject.tasks.withType<SonarTask> {
            dependsOn(this)
        }

        currentProject.tasks.named("check") {
            dependsOn(this)
        }
    }

    currentProject.configure<SonarExtension> {
        properties {
            property(
                "sonar.coverage.jacoco.xmlReportPaths",
                "${rootProject.layout.buildDirectory.get().asFile.absolutePath}/reports/jacoco/testCodeCoverageReport/testCodeCoverageReport.xml"
            )
        }
    }

    currentProject.tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events("passed", "failed", "skipped")
        }

        reports {
            junitXml.required = true
            html.required = true
        }
    }
}