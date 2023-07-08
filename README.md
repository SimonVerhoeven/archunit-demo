# ArchUnit

***
- [About](#about)
- [Defining what is analyzed](#defining-what-is-analyzed)
- [Areas](#areas)
- [Adding ArchUnit to an existing application](#adding-archunit-to-an-existing-application)
- [Customization](#customization)
- [Notes](#notes)
***

## About

[ArchUnit](https://www.archunit.org/) allows us to test our architecture (layering/slicing/(naming) conventions, ...)

Why does this matter? It's all about leaving a legacy, and safeguarding it. During the lifecycle of a project people might shift role, switch role, join the team, ... And might not be aware of the conventions within the team/organization. 

Testing your architecture, is both an aide to ascertain that the architecture is being implemented consistently, and also makes it easier for people onboarding to get a grasp of what it is.

One of the advantages of ArchUnit is also that it "just" another test, and does not need any special infrastructure/new language/... it's just plain old java that can be evaluated with an unit testing lool like JUnit.

***

## Defining what is analyzed

There are a couple of ways to determine what should be analyzed:
* based upon the class(es)
* by package(s)
* a custom location provider
* using the ClassFileImporter directly on packages/path

Examples can be found in the `analysismanagement` package

***

## Areas

### Core

This contains well, the Core api of ArchUnit which offers us ways to access fields, methods, classes, ... (`JavaMethod, JavaField, getMethods(), getRawParametersTypes(), ...`) 

And we can import these using certain provided apis (cfr `dev.simonverhoeven.archunitdemo.analysismanagement\ClassFileImporterTest.java` for some samples albeit there certainly are a lot more options)

As seen you can also add `ImportOptions` to further narrow what's imported. There are also certain predefined ones such as `ImportOption.Predefined.DO_NOT_INCLUDE_JARS`.

A sample of a rule to verify that classes under service do not access anything in the controller package:

````Java
final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.layerviolationmodule");
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
````

As you can see this is a tad cumbersome, and this is where the higher level Lang api comes into play

### Lang

The lang api offers us some nice functionalities to be more expressive about our architectural concepts.

We can rewrite the Core sample to something pretty similiar using:

````Java
final var importedClasses = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo.layerviolationmodule");
final var rule = ArchRuleDefinition.noClasses()
        .that().resideInAPackage("..service..")
        .should().accessClassesThat().resideInAPackage("..controller..");

rule.check(importedClasses);
````

### Library

The library API offers us some nice convenience functions to easily check some common, but complex patterns

* layered architecture
* onion architecture
* slicing
* General coding roles (literally General such as no usage of Joda time, dependency rules, proxy rules)
* using plantuml component diagram as rules

***

## Customization

### Custom rules

We cam also define our own rules that adhere to the general architectural rule of `classes that {PREDICATE} should {CONDITION}` by creating our own implementation of `DescribedPredicate` and `ArchCondition` respectively.

An example can be found [here](src\test\java\dev\simonverhoeven\archunitdemo\customization\CustomPredicateAndConditionTest.java)

### Display format

It is possible to customize the format the generated messages by creating an implementation of `FailureDisplayFormat` and configuring it in `archunit.properties`.

`failureDisplayFormat=dev.simonverhoeven.archunitdemo.customization.UppercasingFailureFormat`

An example implementation can be found [here](src\test\java\dev\simonverhoeven\archunitdemo\customization\UppercasingFailureFormat.java)

***

## Adding ArchUnit to an existing application

In case you want to add `ArchUnit` to an existing application, you might run into a situation where there are a lot of existing violations, this is where `FreezingArchRule` comes into play. 

````Java
FreezingArchRule.freeze(//ArchRule to freeze);
````

This allows you to "accept" the current state of the issues, which will be stored in plain text files by default. And in subsequent runs only new violations will be reported so one can verify that no new ones are being added.

For example if in this demo project one were to uncomment `dataNew` in `LegacyService` and then run the FreezingValidationTest the test would only complain about the new field since we already acknowledged the existing issue. (see for reference [src\test\resources\frozen](src\test\resources\frozen))

The default configuration is done in `src\test\resources\archunit.properties`

And there are a couple of different options:
````
# configure the location of the violation store
freeze.store.default.path=src/test/resources/frozen
# whether a new store should be created, for a CI build you'll likely want to keep this on the default value of false
freeze.store.default.allowStoreCreation=true
# whether the stored violations of frozen rules can be updated, default of true
freeze.store.default.allowStoreUpdate=true
# whether to allow all violations to be refrozen (i.e. update the store with the current state to mark the current violations as accepted, report success)
freeze.refreeze=false
````

It is also possible to configure these using system properties
`-Darchunit.freeze.store.default.allowStoreCreation=true`

There are also 2 extension options for this setup:
* Violation store: you can set up your own implementation of `ViolationStore` and configure ArchUnit to use it
* Violation Line Matcher: you can implement your own `ViolationLineMatcher` to to define how occurred violations should be matched with stored violations. 

Furthermore one can also define an `archunit_ignore_patterns.txt` file in the root of the classpath to ignore violations based upon a regex match.

One can also just tailor their .that() to ignore these legacy classes, but that can quickly become quite cumbersome.

***

## Notes

1) 
It is possible to define easy tests using:
````Java
    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
````

2)
It is not required to use JUnit, you can also import the core archunti dependency to use it with your testing framework.

3)
Akin to JUnit's `@DisplayNameGenerationReplaceUnderscores.class)` it is possible to overwrite the output to replace the underscores with spaces to make it a tad more readable.
This is done by creating an `archunit.properties` file in your `test\resources` folder with: `junit.displayName.replaceUnderscoresBySpaces=true`

4) 
By default ArchUnit will fail on `should()` rules being matched against an empty class set.
This is to avoid rules that are accidentally checked against nothing.

This behaviour can by overwritten either on a case by case basis

<code>classes().should().beEnums()<b>.allowEmptyShould()</b></code>

Or globally by configuring
````
archRule.failOnEmptyShould=false
````
in archunit.properties
