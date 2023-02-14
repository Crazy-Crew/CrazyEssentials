plugins {
    id("essentials.spigot-plugin")
}

dependencies {
    api(project(":crazyessentials-provider-reflection"))

    compileOnly(libs.spigot)
}