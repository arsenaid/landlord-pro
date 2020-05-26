package com.landlord.haus;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.landlord.haus");

        noClasses()
            .that()
            .resideInAnyPackage("com.landlord.haus.service..")
            .or()
            .resideInAnyPackage("com.landlord.haus.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.landlord.haus.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
