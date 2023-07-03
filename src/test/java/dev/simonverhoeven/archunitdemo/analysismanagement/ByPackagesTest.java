package dev.simonverhoeven.archunitdemo.analysismanagement;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;


// When we want to define the packages to analyze
@AnalyzeClasses(packages = "dev.simonverhoeven.archunitdemo.module1")
public class ByPackagesTest {
    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION.because("error from package level analysis");

}
