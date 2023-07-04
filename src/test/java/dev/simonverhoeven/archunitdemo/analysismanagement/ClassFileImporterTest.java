package dev.simonverhoeven.archunitdemo.analysismanagement;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ClassFileImporterTest {

    // Import package(s) to scan
    @Test
    public void packageImport() {
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.legacymodule");

        ArchRule myRule = classes().should().haveSimpleNameContaining("foo");

        myRule.check(importedClasses);
    }

    // Import a path to scan
    @Test
    public void pathImporter() {
        final var importedClasses = new ClassFileImporter().importPath("");

        ArchRule myRule = classes().should().haveSimpleNameContaining("foo");

        myRule.allowEmptyShould(true).check(importedClasses);
    }

}
