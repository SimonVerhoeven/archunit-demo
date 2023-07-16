package dev.simonverhoeven.archunitdemo.customization;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ClassesTransformer;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;

public class CustomConceptsTest {

    @Test
    void transformToFields() {
        ClassesTransformer<JavaField> constantClassFields = new AbstractClassesTransformer<>("Utility fields") {
            @Override
            public Iterable<JavaField> doTransform(JavaClasses classes) {
                Set<JavaField> fields = new HashSet<>();
                for (JavaClass javaClass : classes) {
                    if (javaClass.getSimpleName().endsWith("Constants")) {
                        fields.addAll(javaClass.getFields());
                    }
                }
                return fields;
            }
        };

        final var rule = all(constantClassFields).should(ArchConditions.beStatic().and(ArchConditions.beFinal()));
        final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.transformationmodule");
        rule.check(importedClasses);
    }
}
