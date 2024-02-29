plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(":aoc2015:aoc2015-java"))
    api(project(":aoc2015:aoc2015-kotlin"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "de.havox_design.aoc2015.MainClass"
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "de.havox_design.aoc2015.MainClass"
    }
}

tasks.test{
    dependsOn(project(":aoc2015:aoc2015-python").tasks.named("check"))
}
