plugins {
    id("essentials.spigot-plugin")
}

java {
    disableAutoTargetJvm()
}

dependencies {
    implementation(project(":crazyessentials-provider-base")) {
        exclude(module = "spigot-api")
    }

    compileOnly(libs.papermc)
    compileOnly(libs.papermoj)
}