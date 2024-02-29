dependencies {
    api(project(":utils:utils-java"))
    api(project(":utils:utils-kotlin"))
}

subprojects {
    dependencies {
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.named<Jar>("jar") {
        manifest {
            attributes["Implementation-Title"] = project.name
            attributes["Implementation-Version"] = project.version
        }
    }
}
