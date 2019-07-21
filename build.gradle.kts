plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.github.spullara.mustache.java:compiler:0.9.6")
    implementation("info.picocli:picocli:4.0.1")
    annotationProcessor("info.picocli:picocli-codegen:4.0.1")
}

application {
    mainClassName = "com.splendid.SplendidApp"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}