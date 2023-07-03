package dev.simonverhoeven.archunitdemo.analysismanagement;

import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.core.importer.Locations;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.LocationProvider;
import com.tngtech.archunit.lang.ArchRule;
import dev.simonverhoeven.archunitdemo.legacymodule.LegacyService;

import java.util.HashSet;
import java.util.Set;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

// We can define our own LocationProvider to determine where to look
@AnalyzeClasses(locations = CustomLocationProvider.class)
public class LocationProviderTest {

    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}

class CustomLocationProvider implements LocationProvider {

    @Override
    public Set<Location> get(Class<?> aClass) {
        final Set<Location> result = new HashSet<>();
        result.addAll(Locations.ofClass(LegacyService.class));
        result.addAll(Locations.ofPackage("dev.simonverhoeven.archunitdemo.legacymodule"));
        return result;
    }
}
