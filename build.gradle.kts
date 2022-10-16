import ru.endlesscode.bukkitgradle.dependencies.spigotApi

plugins {
    id("ru.endlesscode.bukkitgradle") version "0.10.1"
}

group = "com.rezplugin"
description = "Kit PvP"
version = "0.1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly(spigotApi)

    testImplementation("com.github.seeseemelk:MockBukkit-v1.19:2.125.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

bukkit {
    apiVersion = "1.19.2"
    server {
        coreType = ru.endlesscode.bukkitgradle.server.extension.CoreType.PAPER
        eula = true
        onlineMode = false
        debug = true
        encoding = "UTF-8"
        javaArgs("-Xmx1G")
        bukkitArgs("nogui")
    }
}