package dev.simonverhoeven.archunitdemo;

import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.metrics.ArchitectureMetrics;
import com.tngtech.archunit.library.metrics.MetricsComponents;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class DependencyMetricsTest {
    final Set<JavaPackage> packages = new ClassFileImporter().importPackages("dev.simonverhoeven.archunitdemo").getPackage("dev.simonverhoeven.archunitdemo").getSubpackages();

    @Test
    void cumulativeDependencyMetrics() {
        final var components = MetricsComponents.fromPackages(packages);
        final var metrics = ArchitectureMetrics.lakosMetrics(components);

        System.out.println("Cumulative Component Dependency: " + metrics.getCumulativeComponentDependency());
        System.out.println("Average Component Dependency: " + metrics.getAverageComponentDependency());
        System.out.println("Relative Average Component Dependency: " + metrics.getRelativeAverageComponentDependency());
        System.out.println("Normalized Cumulative Component Dependency: " + metrics.getNormalizedCumulativeComponentDependency());
    }

    @Test
    void componentDependencyMetrics() {
        final var components = MetricsComponents.fromPackages(packages);
        final var metrics = ArchitectureMetrics.componentDependencyMetrics(components);
        final var componentIdentifier = "dev.simonverhoeven.archunitdemo.onionmodule";

        System.out.println("Efferent Coupling: " + metrics.getEfferentCoupling(componentIdentifier));
        System.out.println("Afferent coupling: " + metrics.getAfferentCoupling(componentIdentifier));
        System.out.println("Instability: " + metrics.getInstability(componentIdentifier));
        System.out.println("Abstractness: " + metrics.getAbstractness(componentIdentifier));
        System.out.println("Normalized distance from main sequence: " + metrics.getNormalizedDistanceFromMainSequence(componentIdentifier));
    }

    @Test
    void visibilityMetrics() {
        final var components = MetricsComponents.fromPackages(packages);
        final var metrics = ArchitectureMetrics.visibilityMetrics(components);
        final var componentIdentifier = "dev.simonverhoeven.archunitdemo.onionmodule";

        System.out.println("Relative Visibility : " + metrics.getRelativeVisibility(componentIdentifier));
        System.out.println("Average Relative Visibility: " + metrics.getAverageRelativeVisibility());
        System.out.println("Global Relative Visibility: " + metrics.getGlobalRelativeVisibility());
    }
}
