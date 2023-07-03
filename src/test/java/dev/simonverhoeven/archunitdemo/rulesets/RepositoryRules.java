package dev.simonverhoeven.archunitdemo.rulesets;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class RepositoryRules {

    @ArchTest
    private final ArchRule repository_definition = classes()
            .that().areAnnotatedWith(Repository.class).or().haveSimpleNameEndingWith("Repository")
            .should().beAnnotatedWith(Repository.class).andShould().haveSimpleNameEndingWith("Repository")
            .because("Consistency in repository definition and naming");

    @ArchTest
    private final ArchRule repository_location = classes()
            .that().areAnnotatedWith(Repository.class).or().haveSimpleNameEndingWith("Repository")
            .should().resideInAPackage("..repository..");
}
