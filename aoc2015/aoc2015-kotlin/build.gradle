plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api project(":utils")

    implementation 'org.jetbrains.kotlin:kotlin-reflect'
    implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8'

    testImplementation 'org.jetbrains.kotlin:kotlin-test-junit'

    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

jar {
    manifest {
        attributes 'Implementation-Title': project.name
        attributes 'Implementation-Version': project.version
    }
}
