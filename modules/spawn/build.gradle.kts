plugins {
    id("essentials.spigot-plugin")
}

dependencies {
    compileOnly(project(":crazyessentials-platform-spigot"))

    compileOnly(libs.spigot)
}

val projectDescription = settings.versions.projectDescription.get()
val projectGithub = settings.versions.projectGithub.get()
val projectGroup = settings.versions.projectGroup.get()
val projectName = settings.versions.projectName.get()
val projectExt = settings.versions.projectExtension.get()

val isBeta = settings.versions.projectBeta.get().toBoolean()

val projectVersion = settings.versions.projectVersion.get()

val finalVersion = if (isBeta) "$projectVersion+Beta" else projectVersion

val projectNameLowerCase = projectName.toLowerCase()

tasks {
    shadowJar {
        archiveFileName.set("${projectName}Spawn+$finalVersion.jar")
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                //"name" to projectName,
                "group" to projectGroup,
                "version" to finalVersion,
                "description" to projectDescription,
                "website" to "https://github.com/Crazy-Crew/Essentials"
            )
        }
    }
}