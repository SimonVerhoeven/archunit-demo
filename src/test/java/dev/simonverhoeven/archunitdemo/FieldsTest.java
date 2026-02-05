package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.domain.properties.HasName;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class FieldsTest {

    @Test
    void noAccessedByMethodsThat() {
        final var importedClasses = new ClassFileImporter().importPath("");

        final var rule = ArchRuleDefinition.fields()
                .that().areDeclaredIn(FieldsClassSample.class)
                .should()
                .notBeAccessedByMethodsThat(HasName.Predicates.name("forbiddenMethod"))
                .because("I want to test that they aren't called by forbidden methods");

        rule.check(importedClasses);
    }

    class FieldsClassSample {
        private String field;

        public void forbiddenMethod() {
            field = "test";
        }
    }
}
