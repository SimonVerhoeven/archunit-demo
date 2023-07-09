package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class SliceTest {

    @Test
    void noCycles() {
        final var rule = slices().matching("dev.simonverhoeven.archunitdemo.slicingmodule.(*)..").should().beFreeOfCycles();
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.slicingmodule");
        rule.check(importedClasses);
    }
    @Test
    void noDependencies() {
        final var rule = slices().matching("dev.simonverhoeven.archunitdemo.slicingmodule.(*)..").should().notDependOnEachOther();
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.slicingmodule");
        rule.check(importedClasses);
    }
}
