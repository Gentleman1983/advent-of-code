dependencies {
    api(project(":utils"))

    implementation(libs.commons.collections4)
    implementation(libs.commons.lang3)

    testImplementation(libs.equalsverifier)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}
