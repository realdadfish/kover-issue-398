@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.api.AndroidBasePlugin
import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.GroupingEntityType
import kotlinx.kover.gradle.plugin.dsl.KoverReportFilters
import kotlinx.kover.gradle.plugin.dsl.KoverVerifyReportConfig
import kotlinx.kover.gradle.plugin.dsl.MetricType

plugins {
    id("org.jetbrains.kotlinx.kover")
}

val coverageExtension = project.extensions.create(
    "codeCoverage", CoverageExtension::class.java
)

// since the plugin does not support lazy configuration and our coverage extension does neither, we need to
// wait until the module's build gradle file is evaluated and has the proper settings filled in
afterEvaluate {
// We can't use Jacoco because of slow class filtering, see https://github.com/Kotlin/kotlinx-kover/issues/398
// kover {
//  useJacoco("0.8.8")
// }
    plugins.withId("org.jetbrains.kotlin.jvm") {
        koverReport {
            filters {
                configure(coverageExtension)
            }
            defaults {
                verify {
                    configure(coverageExtension)
                }
            }
        }
    }
    plugins.withType(AndroidBasePlugin::class.java) {
        koverReport {
            androidReports(coverageExtension.androidVariant) {
                filters {
                    configure(coverageExtension)
                }

                verify {
                    configure(coverageExtension)
                }
            }
        }
    }
}

fun KoverVerifyReportConfig.configure(extension: CoverageExtension) {
    onCheck = true
    rule {
        isEnabled = true
        entity = GroupingEntityType.APPLICATION
        bound {
            minValue = extension.minCoveragePercentage
            maxValue = extension.maxCoveragePercentage
            metric = MetricType.LINE
            aggregation = AggregationType.COVERED_PERCENTAGE
        }
    }
}

fun KoverReportFilters.configure(extension: CoverageExtension) {
    excludes {
        classes(extension.classFilter)
    }
}