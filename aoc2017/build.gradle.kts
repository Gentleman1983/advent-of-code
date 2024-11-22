plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}
val year = 2017

dependencies {
    api(project(":aoc${year}:aoc${year}-java"))
    api(project(":aoc${year}:aoc${year}-kotlin"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "de.havox_design.aoc${year}.MainClass"
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "de.havox_design.aoc${year}.MainClass"
    }
}
