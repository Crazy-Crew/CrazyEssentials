plugins {
    id("essentials.spigot-plugin")
}

dependencies {
    implementation(project(":essentialsx-provider-base"))

    compileOnly(libs.spigot.eight)
}