package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

@AnalyzeClasses(packages = "dev.simonverhoeven.archunitdemo")
public class BasicValidationTest {

    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}
