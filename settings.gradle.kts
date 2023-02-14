@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    includeBuild("build-logic")

    versionCatalogs {
        create("settings") {
            from(files("gradle/settings.versions.toml"))
        }
    }

    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")

        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "CrazyEssentials"

val lowerCase = rootProject.name.toLowerCase()

includePlatform("spigot")

includeProvider("base")
includeProvider("reflection")
includeProvider("legacy")
includeProvider("modern")
includeProvider("paper")

listOf(
    "chat",
    "spawn"
).forEach(::includeModule)

fun includeProject(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
    }
}

fun includePlatform(name: String) {
    include(name) {
        this.name = "$lowerCase-platform-$name"
        this.projectDir = file("platforms/$name")
    }
}

fun includeModule(name: String) {
    include(name) {
        this.name = "$lowerCase-module-$name"
        this.projectDir = file("modules/$name")
    }
}

fun includeProvider(name: String) {
    include(name) {
        this.name = "$lowerCase-provider-$name"
        this.projectDir = file("providers/$name")
    }
}

fun includePlatformModule(name: String, platform: String) {
    include(name) {
        this.name = "$lowerCase-module-$platform-$name"
        this.projectDir = file("modules/$platform/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}