import gradle.kotlin.dsl.accessors._db455dcebdb5f6136d5e8853524c8300.java
import gradle.kotlin.dsl.accessors._db455dcebdb5f6136d5e8853524c8300.license

plugins {
    `java-library`

    `maven-publish`

    id("com.github.hierynomus.license")

    id("com.github.johnrengelman.shadow")
}

repositories {

}

license {
    header = rootProject.file("LICENSE")
    encoding = "UTF-8"

    mapping("java", "JAVADOC_STYLE")

    include("**/*.java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}