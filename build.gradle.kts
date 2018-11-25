plugins {
    application
    kotlin("jvm") version "1.3.0"
}

application {
    mainClassName = "client.ClientKt"
}

dependencies {
    compile(kotlin("stdlib"))

    compile("io.ktor:ktor-client-core:1.0.0")
    compile("io.ktor:ktor-client-apache:1.0.0")
}

repositories {
    mavenCentral()
    jcenter()
}

task("packageClient", type = Jar::class) {
    from(configurations.runtime.map { if (it.isDirectory) it else zipTree(it) })

    manifest {
        attributes["Main-Class"] = "client.ClientKt"
    }
    with(tasks["jar"] as CopySpec)
}

