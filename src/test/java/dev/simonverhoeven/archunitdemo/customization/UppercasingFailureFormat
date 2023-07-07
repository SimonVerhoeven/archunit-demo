package dev.simonverhoeven.archunitdemo.customization;

import com.tngtech.archunit.base.HasDescription;
import com.tngtech.archunit.lang.FailureDisplayFormat;
import com.tngtech.archunit.lang.FailureMessages;
import com.tngtech.archunit.lang.Priority;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class UppercasingFailureFormat implements FailureDisplayFormat {
    @Override
    public String formatFailure(HasDescription rule, FailureMessages failureMessages, Priority priority) {
        String failureDetails = failureMessages.stream()
                .map(String::toUpperCase)
                .collect(joining(lineSeparator()));

        return String.format("ARCHITECTURE VIOLATION [PRIORITY: %s] - RULE '%s' WAS VIOLATED (%s):%n%s",
                priority.asString(),
                rule.getDescription().toUpperCase(),
                failureMessages.getInformationAboutNumberOfViolations(),
                failureDetails);
    }
}
