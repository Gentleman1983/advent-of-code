dependencies {
    api(project(":utils"))

    implementation(libs.commons.codec)
    //implementation(libs.guava)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}
