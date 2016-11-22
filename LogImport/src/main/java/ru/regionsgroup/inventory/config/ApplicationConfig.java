package ru.regionsgroup.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.regionsgroup.inventory.service.audit.source.*;

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
    UserAuditSourceImpl userAuditLocation() {
        return new UserAuditSourceImpl(Paths.get(AD_ROOT), USER_LOG);
    }

    @Bean
    ComputerAuditSourceImpl computerAuditLocation() {
        return new ComputerAuditSourceImpl(Paths.get(AD_ROOT), COMP_LOG);
    }

    @Bean
    DomainAuditSourceImpl domainAuditLocation() {
        return new DomainAuditSourceImpl(Paths.get(AD_ROOT), DOMAIN_LOG);
    }

    @Bean
    PrinterConnectionAuditSourceImpl printerConnectionAuditLocation() {
        return new PrinterConnectionAuditSourceImpl(Paths.get(PRN_ROOT), LOG_FILTER);
    }

    @Bean
    InstalledSoftwareAuditSourceImpl installedSoftwareAuditLocation() {
        return new InstalledSoftwareAuditSourceImpl(Paths.get(SOFT_ROOT), LOG_FILTER);
    }
}
