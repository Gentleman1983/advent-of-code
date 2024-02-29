plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(":aoc2016:aoc2016-groovy"))
    api(project(":aoc2016:aoc2016-java"))
    api(project(":aoc2016:aoc2016-kotlin"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "de.havox_design.aoc2016.MainClass"
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "de.havox_design.aoc2016.MainClass"
    }
}