import kotlin.math.max
import kotlin.math.min

open class CoverageExtension {
    /**
     * The name of the Android variant / source set to use for coverage calculation
     *
     * Note that only a single variant can be configured here, because multiple variants might
     * share common classes that will otherwise cause conflicts when eventually merging the
     * coverage results.
     */
    var androidVariant: String = "debug"

    /**
     * Expected module coverage, never decrease, only increase!
     */
    var expectedCoverage: Float = 0.80f

    /**
     * Coverage threshold in which a calculated coverage value is valid
     * minCoverage = expectedCoverage - minCoverageThreshold
     */
    var minCoverageThreshold: Float = 0.01f

    /**
     * coverage threshold in which a calculated coverage value is valid
     * maxCoverage = expectedCoverage + maxCoverageThreshold
     */
    var maxCoverageThreshold: Float = 0.10f

    /**
     *  Jacoco works with class (bytecode) filters, so we filter out things that we can't / don't want to cover
     *
     *  Add your own filters via
     *  ```
     *  classFilter += listOf("MyClass.*")
     *  ```
     */
    var classFilter: List<String> = listOf(
        // Generated Android classes
        "**/R.class",
        "**/BR.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        // Generated data binding classes
        "**/databinding/*Binding*.class",
        "**/DataBinderMapperImpl.class",
        // Generated room classes
        "**/*_Impl.class",
        "**/*_Impl$*.class",
        // Generated Navigation library classes
        "**/*Args.class",
        "**/*Args\$Companion.class",
        "**/*Directions.class",
        "**/*Directions\$Companion.class",
        // Generated Dagger classes and provisions
        "**/*Module.*",
        "**/generated/**/*.*",
        "**/*_MembersInjector.*",
        "**/Dagger*",
        "**/Hilt_*",
        "**/hilt_aggregated_deps/**/*.*",
        "**/*_Factory.*",
        "**/*Factory.*",
        "**/*Module$*.*",
        // Other classes
        "**/*JsonAdapter.*"
    )

    internal val minCoveragePercentage: Int
        get() = (max(0f, expectedCoverage - minCoverageThreshold) * 100).toInt()

    internal val maxCoveragePercentage: Int
        get() = (min(1f, expectedCoverage + maxCoverageThreshold) * 100).toInt()
}