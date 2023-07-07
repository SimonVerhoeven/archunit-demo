package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CoreVsLangTest {

    // Test that services do not access controllers using the core API
    @Test
    void core_controllers_not_aaccessed_from_services() {
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.servicecontroller");
        final var services = importedClasses.stream()
                .filter(clazz -> clazz.isAnnotatedWith(Service.class) || clazz.getName().contains(".service."))
                .collect(Collectors.toSet());

        services.forEach(service -> {
            service.getAccessesFromSelf().forEach(access -> {
                final var targetName = access.getTargetOwner().getName();

                if (targetName.contains(".controller.")) {
                    final var message = String.format("Service %s accesses Controller %s in line %d",
                            service.getName(), targetName, access.getLineNumber());
                    fail(message);
                }
            });
        });
    }

    @Test
    void lang_controllers_not_accessed_from_services() {
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.servicecontroller");
        final var rule = ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..service..")
                .should().accessClassesThat().resideInAPackage("..controller..");

        rule.check(importedClasses);
    }
}
