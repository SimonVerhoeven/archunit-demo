package dev.simonverhoeven.archunitdemo.customization;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class CustomPredicateAndConditionTest {

    @Test
    void controllerCheck() {
        var resembleControllerPredicate = new DescribedPredicate<JavaClass>("resemble a controller") {

            @Override
            public boolean test(JavaClass input) {
                return input.isAnnotatedWith(RestController.class) || input.getName().endsWith("Controller") || "controller".equalsIgnoreCase(input.getPackage().getRelativeName());
            }
        };

        var beDefinedAsControllerCondition = new ArchCondition<JavaClass>("should be defined as a controller") {

            @Override
            public void check(JavaClass input, ConditionEvents conditionEvents) {
                if (!(input.isAnnotatedWith(RestController.class) && input.getName().endsWith("Controller") && "controller".equalsIgnoreCase(input.getPackage().getRelativeName()))) {
                    final var message = String.format("Class %s does not adhere to the controller conditions", input.getName());
                    conditionEvents.add(SimpleConditionEvent.violated(input, message));
                }
            }
        };

        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.custommodule");
        final var rule = classes().that(resembleControllerPredicate).should(beDefinedAsControllerCondition);
        rule.check(importedClasses);
    }
}
