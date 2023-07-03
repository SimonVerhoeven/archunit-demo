package dev.simonverhoeven.archunitdemo.rulesets;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ServiceRules {

    @ArchTest
    private final ArchRule service_definition = classes()
            .that().areAnnotatedWith(Service.class).or().haveSimpleNameEndingWith("Service")
            .should().beAnnotatedWith(Service.class).andShould().haveSimpleNameEndingWith("Service")
            .because("Consistency in service definition and naming");

    @ArchTest
    private final ArchRule service_location = classes()
            .that().areAnnotatedWith(Service.class).or().haveSimpleNameEndingWith("Service")
            .should().resideInAPackage("..service..");
}
