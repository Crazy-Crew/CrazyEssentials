plugins {
    id("essentials.root-plugin")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.3") {
        exclude(module = "opus-java")
    }
}