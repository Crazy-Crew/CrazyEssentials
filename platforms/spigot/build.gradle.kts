plugins {
    id("essentials.spigot-plugin")
}

dependencies {
    compileOnly(libs.spigot)

    compileOnly(libs.luckperms)

    api(libs.paperlib)

    implementation(libs.configurate)

    compileOnly(libs.vault)

    api(project(":crazyessentials-provider-base"))
    api(project(":crazyessentials-provider-legacy")) {
        exclude(group = "org.spigotmc", module = "spigot")
    }
    api(project(":crazyessentials-provider-modern")) {
        exclude(group = "org.bukkit", module = "bukkit")
    }
    api(project(":crazyessentials-provider-paper"))
    api(project(":crazyessentials-provider-reflection")) {
        exclude(group = "org.bukkit", module = "bukkit")
    }
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
        archiveFileName.set("${projectName}+$finalVersion.jar")

        listOf(
            "io.papermc.lib",
            "org.spongepowered.configurate",
            "me.carleslc.Simple-YAML",
            "io.leangen.geantyref"
        ).forEach {
            relocate(it, "com.earth2me.essentials.plugin.$it")
        }
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