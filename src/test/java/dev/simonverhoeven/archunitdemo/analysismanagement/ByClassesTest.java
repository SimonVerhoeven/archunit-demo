package dev.simonverhoeven.archunitdemo.analysismanagement;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import dev.simonverhoeven.archunitdemo.legacymodule.LegacyService;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

// When a class might shift location, and we want to determine its package location
@AnalyzeClasses(packagesOf = LegacyService.class)
public class ByClassesTest {

    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION.because("error from package level analysis");
}
