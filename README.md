JDK 17 needed.

Example project showing that late / `afterEvaluate` configuration of kotlinx-kover 0.7.x breaks coverage reporting and verification with Android Projects.

Compare

```
./gradlew lib:koverVerify
```

which correctly reports

```
> A failure occurred while executing kotlinx.kover.gradle.plugin.tools.kover.VerifyReportAction
   > Rule violated: lines covered percentage is 50.000000, but expected maximum is 40
```

against

```
./gradlew app:koverVerifyDebug
```

which falsely runs green.
