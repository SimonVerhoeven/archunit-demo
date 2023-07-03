# ArchUnit

***
- [About](#about)
- [Defining what is analyzed](#defining-what-is-analyzed)
- [Adding ArchUnit to an existing application](#adding-archunit-to-an-existing-application)
- [Notes](#notes)
***

## About

[ArchUnit](https://www.archunit.org/) allows us to test our architecture.

Why does this matter? It's all about leaving a legacy, and safeguarding it. During the lifecycle of a project people might shift role, switch role, join the team, ... And might not be aware of the conventions within the team/organization. 

Testing your architecture, is both an aide to ascertain that the architecture is being implemented consistently, and also makes it easier for people onboarding to get a grasp of what it is.

One of the advantages of ArchUnit is also that it "just" another test, and does not need any special infrastructure/new language/... it's just plain old java that can be evaluated with an unit testing lool like JUnit.

***

## Defining what is analyzed

There are a couple of ways to determine what should be analyzed:
* based upon the class(es)
* by package(s)
* a custom location provider

Examples can be found in the `analysismanagement` package

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

***

## Notes

It is possible to define easy tests using:
````Java
    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
````

Now akin to JUnit's `@DisplayNameGenerationReplaceUnderscores.class)` it is possible to overwrite the output to replace the underscores with spaces to make it a tad more readable.
This is done by creating an `archunit.properties` file in your `test\resources` folder with: `junit.displayName.replaceUnderscoresBySpaces=true`
