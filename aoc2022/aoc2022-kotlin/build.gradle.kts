plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(":utils"))

    implementation(libs.commons.lang3)
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation(libs.equalsverifier)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}
