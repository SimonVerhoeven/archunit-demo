package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayerTest {

    @Test
    void layered() {
        final var architectureRule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .layer("Persistence").definedBy("..persistence..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");

        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.layeredmodule");
        architectureRule.check(importedClasses);
    }
}
