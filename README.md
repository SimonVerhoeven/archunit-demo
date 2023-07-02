# ArchUnit

***
* [About](#about)
* [Notes](#notes)
***

## About

[ArchUnit](https://www.archunit.org/) allows us to test our architecture.

Why does this matter? It's all about leaving a legacy, and safeguarding it. During the lifecycle of a project people might shift role, switch role, join the team, ... And might not be aware of the conventions within the team/organization. 

Testing your architecture, is both an aide to ascertain that the architecture is being implemented consistently, and also makes it easier for people onboarding to get a grasp of what it is.

One of the advantages of ArchUnit is also that it "just" another test, and does not need any special infrastructure/new language/... it's just plain old java that can be evaluated with an unit testing lool like JUnit.

***

## Notes

It is possible to define easy tests using:
````Java
    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
````

Now akin to JUnit's `@DisplayNameGenerationReplaceUnderscores.class)` it is possible to overwrite the output to replace the underscores with spaces to make it a tad more readable.
This is done by creating an `archunit.properties` file in your `test\resources` folder with: `junit.displayName.replaceUnderscoresBySpaces=true`