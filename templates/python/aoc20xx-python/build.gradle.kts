import com.pswidersk.gradle.python.PythonPluginExtension
import com.pswidersk.gradle.python.VenvTask
import org.sonarqube.gradle.SonarExtension

plugins {
    alias(libs.plugins.python.plugin)
}

val year = "20xx"

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

configure<SonarExtension> {
    properties {
        property("sonar.python.version", "3.12")
        property("sonar.python.coverage.reportPaths", "build/reports/pytest-cov/coverage.xml")
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

afterEvaluate {
    val pipTask = tasks.register<VenvTask>("pipInstall") {
        group = "python"
        description = "Prepare Python runtime."

        venvExec = "pip"
        args = listOf("install", "--isolated", "-r", "requirements.txt")
    }

    val riddles = listof(
        Pair("01", "1"), Pair("01", "2"),
        Pair("02", "1"), Pair("02", "2"),
        Pair("03", "1"), Pair("03", "2"),
        Pair("04", "1"), Pair("04", "2"),
        Pair("05", "1"), Pair("05", "2"),
        Pair("06", "1"), Pair("06", "2"),
        Pair("07", "1"), Pair("07", "2"),
        Pair("08", "1"), Pair("08", "2"),
        Pair("09", "1"), Pair("09", "2"),
        Pair("10", "1"), Pair("10", "2"),
        Pair("11", "1"), Pair("11", "2"),
        Pair("12", "1"), Pair("12", "2"),
        Pair("13", "1"), Pair("13", "2"),
        Pair("14", "1"), Pair("14", "2"),
        Pair("15", "1"), Pair("15", "2"),
        Pair("16", "1"), Pair("16", "2"),
        Pair("17", "1"), Pair("17", "2"),
        Pair("18", "1"), Pair("18", "2"),
        Pair("19", "1"), Pair("19", "2"),
        Pair("20", "1"), Pair("20", "2"),
        Pair("21", "1"), Pair("21", "2"),
        Pair("22", "1"), Pair("22", "2"),
        Pair("23", "1"), Pair("23", "2"),
        Pair("24", "1"), Pair("24", "2"),
        Pair("25", "1"), Pair("25", "2")
    )

    for(val riddle in riddles) {
        val day = riddle.first
        val part = riddle.second

        val solveRiddleTask = tasks.register<VenvTask>("solveDay${day}Part${part}") {
            group = "python"
            description = "Solves AoC ${year} Day ${day} part ${part}."

            workingDir = project.file("src/main/resources")
            args = listOf(project.file("src/main/python/day${day}pt${part}.py").toString())
            dependsOn(pipTask)
        }

        val testRiddleTask = tasks.register<VenvTask>("testSolveDay${day}Part${part}") {
            group = "python"
            description = "Tests AoC ${year} Day ${day} part ${part}."

            venvExec = "pytest"
            workingDir = project.file("src/test/resources")
            args = listOf(
                "--cov-report", "term",
                "--cov-report", "html:${project.layout.buildDirectory.file("reports/pytest-cov").get().asFile.canonicalPath}",
                "--cov-report", "xml:${project.layout.buildDirectory.file("reports/pytest-cov/coverage.xml").get().asFile.canonicalPath}",
                "--cov-report", "json:${project.layout.buildDirectory.file("reports/pytest-cov/coverage.json").get().asFile.canonicalPath}",
                "--cov-report", "lcov:${project.layout.buildDirectory.file("reports/pytest-cov/coverage.lcov").get().asFile.canonicalPath}",
                "--cov=day${day}pt${part}",
                project.file("src/test/python/testDay${day}pt${part}.py").toString()
            )
            environment = mapOf(
                Pair("PYTHONPATH", project.file("src/main/python").getCanonicalPath())
            )
            dependsOn(pipTask)
        }

        tasks.named<JavaCompile>("compileJava") {
            dependsOn(solveRiddleTask)
        }

        tasks.check {
            dependsOn(testRiddleTask)
        }
    }
}
