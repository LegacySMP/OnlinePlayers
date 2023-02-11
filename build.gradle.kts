plugins {
    id("java")
}

group = "me.allinkdev"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenLocal()
}

dependencies {
    compileOnly("org.bukkit:project-poseidon:1.1.7")
}