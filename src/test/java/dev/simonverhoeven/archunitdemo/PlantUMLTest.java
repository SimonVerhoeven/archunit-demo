package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition.Configuration.consideringOnlyDependenciesInAnyPackage;
import static com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition.adhereToPlantUmlDiagram;

public class PlantUMLTest {

    @Test
    void verifyStructure() {
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo");
        final var diagram = getClass().getClassLoader().getResource("diagram.puml");
        classes().should(adhereToPlantUmlDiagram(diagram, consideringOnlyDependenciesInAnyPackage("..plantmodule.."))).check(importedClasses);
    }
}
