plugins {
    id("essentials.spigot-plugin")
}

dependencies {
    api(project(":essentialsx-provider-reflection"))

    compileOnly(libs.spigot)
}