package ru.regionsgroup.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.regionsgroup.inventory.model.format.BooleanDeserializer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMPUTERS")
public class Computer {
    @Id
    @JsonProperty(value = "id")
    @Column(name = "COMPUTER_ID")
    private String id;

    @JsonProperty(value = "cn")
    @Column(name = "COMPUTER_NAME")
    private String computerName;

    @JsonProperty(value = "samAccountName")
    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @JsonProperty
    @Column(name = "DNS_DOMAIN")
    private String dnsDomain;

    @JsonProperty
    @Column(name = "DESCRIPTION")
    private String description;

    @JsonProperty
    @JsonDeserialize(using = BooleanDeserializer.class)
    @Column(name = "DISABLED")
    private boolean disabled;

    @JsonProperty(value = "isWorkstation")
    @JsonDeserialize(using = BooleanDeserializer.class)
    @Column(name = "WORKSTATION")
    private boolean workstation;

    @JsonProperty(value = "lastLogonTimeStamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "LAST_LOGON")
    @Temporal(TemporalType.DATE)
    private Date lastLogon;

    @JsonProperty(value = "createTimeStamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "CREATED")
    @Temporal(TemporalType.DATE)
    private Date created;

    @JsonProperty
    @Column(name = "OPERATING_SYSTEM")
    private String operatingSystem;

    @JsonProperty(value = "operatingSystemServicePack")
    @Column(name = "SERVICE_PACK")
    private String servicePack;

    @JsonProperty
    @Column(name = "DISTINGUISHED_NAME")
    private String distinguishedName;

    @JsonProperty
    @Column(name = "MANAGED_BY")
    private String managedBy;

    public Computer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDnsDomain() {
        return dnsDomain;
    }

    public void setDnsDomain(String dnsDomain) {
        this.dnsDomain = dnsDomain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isWorkstation() {
        return workstation;
    }

    public void setWorkstation(boolean workstation) {
        this.workstation = workstation;
    }

    public Date getLastLogon() {
        return lastLogon;
    }

    public void setLastLogon(Date lastLogon) {
        this.lastLogon = lastLogon;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getServicePack() {
        return servicePack;
    }

    public void setServicePack(String servicePack) {
        this.servicePack = servicePack;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public String getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(String managedBy) {
        this.managedBy = managedBy;
    }
}
