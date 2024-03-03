plugins {
    // Native SonarCloud support of Groovy is not currently available, so we have to wait for something to change or
    // miss that data.
    groovy
}

dependencies {
    api(project(":utils"))

    api(libs.groovy.all)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}
