plugins {
    id("essentials.spigot-plugin")
}

dependencies {
    implementation(project(":crazyessentials-provider-base"))

    compileOnly(libs.spigot.twelve)
}