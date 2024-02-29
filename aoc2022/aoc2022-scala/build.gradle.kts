plugins {
    scala
}

dependencies {
    api(project(":utils"))

    implementation(libs.scala3.library)

    implementation(libs.scala3.scalatest)
    implementation(libs.scala3.scalatestplus.junit)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
