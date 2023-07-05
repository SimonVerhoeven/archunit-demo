package dev.simonverhoeven.archunitdemo.analysismanagement;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ClassFileImporterTest {

    // Import package(s) to scan
    @Test
    public void packageImport() {
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.legacymodule");
        final var rule = classes().should().haveSimpleNameContaining("SV");
        rule.check(importedClasses);
    }

    // Import a path to scan
    @Test
    public void pathImporter() {
        final var importedClasses = new ClassFileImporter().importPath("");
        final var rule = classes().should().haveSimpleNameContaining("SV");
        rule.allowEmptyShould(true).check(importedClasses);
    }

    @Test
    public void classpathImporter() {
        final var ignoreTestsOption = new ImportOption() {
            @Override
            public boolean includes(Location location) {
                return !location.contains("/test/"); // ignore any URI to sources that contains '/test/'
            }
        };

        final var classes = new ClassFileImporter().withImportOption(ignoreTestsOption).importClasspath();
        final var rule = classes().should().haveSimpleNameContaining("SV");
        rule.check(classes);
    }

}
