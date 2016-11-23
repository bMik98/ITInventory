package ru.regionsgroup.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.regionsgroup.inventory.model.*;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.AuditSource;
import ru.regionsgroup.inventory.service.audit.impl.AuditFileSource;
import ru.regionsgroup.inventory.service.audit.impl.AbstractAuditLoader;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Mbedritskiy
 */
@Configuration
@ComponentScan(basePackages = "ru.regionsgroup.inventory")
public class AuditSourceConfig {
    private final String PRN_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
    private final String SOFT_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Soft";
    private final String AD_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD";
    private final String COMP_LOG = "ad_computers.json";
    private final String USER_LOG = "ad_users.json";
    private final String DOMAIN_LOG = "ad_domains.json";
    private final String LOG_FILTER = "*.json";

    @Autowired
    private Environment environment;

    @Bean
    public UserAuditSource getUserAuditSource() {
        return new UserAuditSource(Paths.get(AD_ROOT), USER_LOG, Charset.forName("windows-1251"));
    }

    @Bean
    public ComputerAuditSource getComputerAuditSource() {
        return new ComputerAuditSource(Paths.get(AD_ROOT), COMP_LOG, Charset.forName("windows-1251"));
    }

    @Bean
    public DomainAuditSource getDomainAuditSource() {
        return new DomainAuditSource(Paths.get(AD_ROOT), DOMAIN_LOG, Charset.forName("windows-1251"));
    }

    @Bean
    public PrinterConnectionAuditSource getPrinterConnectionAuditSource() {
        return new PrinterConnectionAuditSource(Paths.get(PRN_ROOT), LOG_FILTER, Charset.forName("UTF-16LE"));
    }

    @Bean
    public InstalledSoftwareAuditSource getInstalledSoftwareAuditSource() {
        return new InstalledSoftwareAuditSource(Paths.get(SOFT_ROOT), LOG_FILTER, Charset.forName("UTF-16LE"));
    }

    public class ComputerAuditSource extends AuditFileSource {
        public ComputerAuditSource(Path directoryPath, String filter, Charset charset) {
            super(directoryPath, filter, charset);
        }
    }

    public class DomainAuditSource extends AuditFileSource {
        public DomainAuditSource(Path directoryPath, String filter, Charset charset) {
            super(directoryPath, filter, charset);
        }
    }

    public class UserAuditSource extends AuditFileSource {
        public UserAuditSource(Path directoryPath, String filter, Charset charset) {
            super(directoryPath, filter, charset);
        }
    }

    public class InstalledSoftwareAuditSource extends AuditFileSource {
        public InstalledSoftwareAuditSource(Path directoryPath, String filter, Charset charset) {
            super(directoryPath, filter, charset);
        }
    }

    public class PrinterConnectionAuditSource extends AuditFileSource {
        public PrinterConnectionAuditSource(Path directoryPath, String filter, Charset charset) {
            super(directoryPath, filter, charset);
        }
    }
}
