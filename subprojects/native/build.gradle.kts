plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.jmh")
}

description = "This project contains various native operating system integration utilities"

gradlebuildJava.usedInWorkers()

dependencies {
    api(project(":files"))

    implementation(project(":base-services"))
    implementation(project(":file-temp"))

    implementation(libs.nativePlatform)
    implementation(libs.nativePlatformFileEvents)
    implementation(libs.slf4jApi)
    implementation(libs.guava)
    implementation(libs.commonsIo)
    implementation(libs.jansi)
    implementation(libs.inject)

    testImplementation(testFixtures(project(":core")))
    testImplementation(testFixtures(project(":logging")))

    jmhImplementation(project(":files"))
}

jmh {
    fork.set(1)
    threads.set(2)
    warmupIterations.set(10)
    synchronizeIterations.set(false)
}
