import com.pswidersk.gradle.python.PythonPluginExtension
import com.pswidersk.gradle.python.VenvTask
import org.sonarqube.gradle.SonarExtension

plugins {
    alias(libs.plugins.python.plugin)
}

dependencies {
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("scalatest")
    }
}

configure<PythonPluginExtension> {
    pythonVersion = "3.12"
    condaVersion = "latest"
    installDir = file(rootProject.layout.buildDirectory.file("python"))
}

afterEvaluate {
    val pipTask = tasks.register<VenvTask>("pipInstall") {
        group = "python"
        description = "Prepare Python runtime."

        venvExec = "pip"
        args = listOf("install", "--isolated", "-r", "requirements.txt")
    }

    val solveDay19Pt2Task = tasks.register<VenvTask>("solveDay19Part2") {
        group = "python"
        description = "Solves the 2nd part of AoC 2015 Day 19."

        workingDir = project.file("src/main/resources")
        args = listOf(project.file("src/main/python/solveDay19Part2.py").toString())
        dependsOn(pipTask)
    }

    val testSolveDay19Part2Task = tasks.register<VenvTask>("testSolveDay19Part2") {
        group = "python"
        description = "Tests the 2nd part of AoC 2015 Day 19."

        venvExec = "pytest"
        workingDir = project.file("src/test/resources")
        args = listOf(
            "--cov-report", "term",
            "--cov-report", "html:${project.layout.buildDirectory.file("reports/pytest-cov").get().asFile.canonicalPath}",
            "--cov-report", "xml:${project.layout.buildDirectory.file("reports/pytest-cov/coverage.xml").get().asFile.canonicalPath}",
            "--cov-report", "json:${project.layout.buildDirectory.file("reports/pytest-cov/coverage.json").get().asFile.canonicalPath}",
            "--cov-report", "lcov:${project.layout.buildDirectory.file("reports/pytest-cov/coverage.lcov").get().asFile.canonicalPath}",
            "--cov=solveDay19Part2",
            project.file("src/test/python/testSolveDay19Part2.py").toString()
        )
        environment = mapOf(
            Pair("PYTHONPATH", project.file("src/main/python").getCanonicalPath())
        )
        dependsOn(pipTask)
    }

    tasks.named<JavaCompile>("compileJava") {
        dependsOn(solveDay19Pt2Task)
    }

    tasks.check {
        dependsOn(testSolveDay19Part2Task)
    }
}

sourceSets {
    main {
        java {
            srcDir(project.file("src/main/python"))
        }
    }
    test {
        java {
            srcDir(project.file("src/test/python"))
        }
    }
}

configure<SonarExtension> {
    properties {
        property("sonar.python.version", "3.12")
        property("sonar.python.coverage.reportPaths", "build/reports/pytest-cov/coverage.xml")
    }
}
