package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(packages = "dev.simonverhoeven.archunitdemo")
public class BasicValidationTest {

    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    private final ArchRule no_joda_time = NO_CLASSES_SHOULD_USE_JODATIME;

    @ArchTest
    private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule no_standard_streams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    private final ArchRule repository_only_accessed_from_services = classes().that().areAnnotatedWith(Repository.class).should().onlyBeAccessed().byClassesThat().areAnnotatedWith(Service.class);

    @ArchTest
    private final ArchRule controller_definition = classes()
            .that().areAnnotatedWith(RestController.class).or().haveSimpleNameEndingWith("Controller")
            .should().beAnnotatedWith(RestController.class).andShould().haveSimpleNameEndingWith("Controller")
            .because("Consistency in controller definition and naming");
}
