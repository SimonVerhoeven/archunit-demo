package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import java.util.Vector;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.library.freeze.FreezingArchRule.freeze;

@AnalyzeClasses(packages = "dev.simonverhoeven.archunitdemo")
public class FreezingValidationTest {

    @ArchTest
    private final ArchRule no_vector_usage = freeze(noFields().should().haveRawType(Vector.class).because("We agreed to switch to ArrayList<T>"));
}
