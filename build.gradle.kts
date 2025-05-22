import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.sonarqube.gradle.SonarExtension
import org.sonarqube.gradle.SonarTask

plugins {
    id("jacoco-report-aggregation")
    id("java-library")
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.sonarqube.plugin)
}

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
    gradleVersion = "8.14.1"
}

develocity {
    buildScan {
        termsOfUseUrl.set("https://gradle.com/help/legal-terms-of-use")
        termsOfUseAgree.set("yes")
    }
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
        toolVersion = "0.8.13"
    }

    val jacocoTestReportTask = currentProject.tasks.named<JacocoReport>("jacocoTestReport") {
        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }

    currentProject.tasks.named("check") {
        dependsOn(jacocoTestReportTask)
    }

    rootProject.tasks.withType<SonarTask> {
        dependsOn(jacocoTestReportTask)
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

        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)

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
