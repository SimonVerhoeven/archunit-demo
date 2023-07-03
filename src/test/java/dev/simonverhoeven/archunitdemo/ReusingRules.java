package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import dev.simonverhoeven.archunitdemo.rulesets.RepositoryRules;
import dev.simonverhoeven.archunitdemo.rulesets.ServiceRules;

@AnalyzeClasses(packages = "dev.simonverhoeven.archunitdemo")
public class ReusingRules {

    @ArchTest
    static final ArchTests repositoryRules = ArchTests.in(RepositoryRules.class);
    @ArchTest
    static final ArchTests serviceRules = ArchTests.in(ServiceRules.class);

}
