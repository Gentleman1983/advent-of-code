plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(":aoc2018:aoc2018-java"))
    api(project(":aoc2018:aoc2018-kotlin"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "de.havox_design.aoc2018.MainClass"
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "de.havox_design.aoc2018.MainClass"
    }
}