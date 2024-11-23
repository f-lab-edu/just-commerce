plugins {
    java
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-starter-web")
}
