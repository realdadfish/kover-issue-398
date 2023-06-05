plugins {
    `kotlin-dsl`
}
dependencies {
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.1")
    compileOnly("com.android.tools.build:gradle:8.0.2")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
}