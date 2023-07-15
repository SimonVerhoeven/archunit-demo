package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class PredefinedPredicatesAndConditionsTest {

    @Test
    void baseControllerShouldBeRestControllerAndNotEnum() {
        final var predicate = JavaClass.Predicates.simpleNameStartingWith("Base").and(JavaClass.Predicates.simpleNameEndingWith("Controller"));

        // This Will not work given here .and will expect ? super HasAnnotations
        // final ArchCondition<JavaClass> condition = ArchConditions.beAnnotatedWith(Controller.class).and(ArchConditions.beEnums());

        // This will work since when we apply the enums condition the compiled will see the condition as being for JavaClass
        ArchCondition<JavaClass> condition = ArchConditions.beAnnotatedWith(RestController.class);
        condition = condition.and(ArchConditions.notBeEnums());

        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.basemodule");
        final var rule = classes().that(predicate).should(condition);
        rule.check(importedClasses);
    }
}
