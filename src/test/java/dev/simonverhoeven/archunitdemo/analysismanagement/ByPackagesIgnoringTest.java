package dev.simonverhoeven.archunitdemo.analysismanagement;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;


// We can customize some importoptions to narrow what's analyzed (one can implement their own ImportOption implementation)
@AnalyzeClasses(packages = "dev.simonverhoeven.archunitdemo.basemodule", importOptions = ImportOption.DoNotIncludeTests.class)
public class ByPackagesIgnoringTest {
    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION.because("error from package level analysis");
}
