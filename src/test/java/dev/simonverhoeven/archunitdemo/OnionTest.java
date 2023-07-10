package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

public class OnionTest {

    @Test
    void onion() {
        final var rule = onionArchitecture()
                .domainModels("..domain.model..")
                .domainServices("..domain.service..")
                .applicationServices("..application..")
                .adapter("persistence", "..adapter.persistence..")
                .adapter("rest", "..adapter.rest..")
                .ensureAllClassesAreContainedInArchitecture();

        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.onionmodule");
        rule.check(importedClasses);
    }
}
