plugins {
    id("org.jetbrains.kotlin.jvm")
    id("ris.code-coverage")
}

codeCoverage {
    expectedCoverage = 0.3f
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}