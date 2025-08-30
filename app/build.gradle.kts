plugins {
    checkstyle
    application
    jacoco
    id("org.sonarqube") version "6.2.0.5505"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.freefair.lombok") version "8.6"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.7.0")
    implementation("io.javalin:javalin-rendering:6.7.0")
    implementation("io.javalin:javalin-bundle:6.7.0")
    implementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("com.zaxxer:HikariCP:7.0.2")
    implementation("com.h2database:h2:2.3.232")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.3")
    implementation("org.postgresql:postgresql:42.7.7")
    implementation("gg.jte:jte:3.2.1")
    implementation("com.konghq:unirest-java-core:4.5.0")
    implementation("org.jsoup:jsoup:1.21.2")
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport { reports { xml.required.set(true) } }

sonar {
    properties {
        property("sonar.projectKey", "liadiann_java-project-72")
        property("sonar.organization", "liadiann")
    }
}
