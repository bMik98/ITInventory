package ru.regionsgroup.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.regionsgroup.inventory.service.audit.location.*;

import java.nio.file.Paths;

/**
 * @author Mbedritskiy
 */
@Configuration
//@ComponentScan({"ru.regionsgroup.inventory.dao.impl", "ru.regionsgroup.inventory.service.audit.*"})
@ComponentScan(basePackages = "ru.regionsgroup.inventory")
public class ApplicationConfig {
    private final String PRN_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
    private final String SOFT_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Soft";
    private final String AD_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD";
    private final String COMP_LOG = "ad_computers.json";
    private final String USER_LOG = "ad_users.json";
    private final String DOMAIN_LOG = "ad_domains.json";
    private final String LOG_FILTER = "*.json";

    @Bean
    UserAuditLocation userAuditLocation() {
        return new UserAuditLocation(Paths.get(AD_ROOT), USER_LOG);
    }

    @Bean
    ComputerAuditLocation computerAuditLocation() {
        return new ComputerAuditLocation(Paths.get(AD_ROOT), COMP_LOG);
    }

    @Bean
    DomainAuditLocation domainAuditLocation() {
        return new DomainAuditLocation(Paths.get(AD_ROOT), DOMAIN_LOG);
    }

    @Bean
    PrinterConnectionAuditLocation printerConnectionAuditLocation() {
        return new PrinterConnectionAuditLocation(Paths.get(PRN_ROOT), LOG_FILTER);
    }

    @Bean
    InstalledSoftwareAuditLocation installedSoftwareAuditLocation() {
        return new InstalledSoftwareAuditLocation(Paths.get(SOFT_ROOT), LOG_FILTER);
    }
}
