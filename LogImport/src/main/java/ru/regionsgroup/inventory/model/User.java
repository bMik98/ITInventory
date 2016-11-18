package ru.regionsgroup.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.regionsgroup.inventory.model.format.BooleanDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @JsonProperty("id")
    @Column(name = "USER_ID")
    private String userId;

    @JsonProperty
    @Column(name = "DNS_DOMAIN")
    private String dnsDomain;

    @JsonProperty(value = "samAccountName")
    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @JsonProperty(value = "userPrincipalName")
    @Column(name = "PRINCIPAL_NAME")
    private String principalName;

    @JsonProperty
    @Column(name = "DISTINGUISHED_NAME")
    private String distinguishedName;

    @JsonProperty
    @JsonDeserialize(using = BooleanDeserializer.class)
    @Column(name = "DISABLED")
    private boolean disabled;

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
    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @JsonProperty
    @Column(name = "CANONICAL_NAME")
    private String canonicalName;

    @JsonProperty
    @Column(name = "DESCRIPTION")
    private String description;

    @JsonProperty
    @Column(name = "COMPANY")
    private String company;

    @JsonProperty
    @Column(name = "DEPARTMENT")
    private String department;

    @JsonProperty
    @Column(name = "TITLE")
    private String title;

    @JsonProperty
    @Column(name = "MAIL")
    private String mail;

    @JsonProperty
    @Column(name = "PROFILE_PATH")
    private String profilePath;

    @JsonProperty
    @Column(name = "SCRIPT_PATH")
    private String scriptPath;

    @JsonProperty
    @Column(name = "HOME_DIRECTORY")
    private String homeDirectory;

    @JsonProperty
    @Column(name = "HOME_DRIVE")
    private String homeDrive;

    @JsonProperty
    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDnsDomain() {
        return dnsDomain;
    }

    public void setDnsDomain(String dnsDomain) {
        this.dnsDomain = dnsDomain;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getHomeDrive() {
        return homeDrive;
    }

    public void setHomeDrive(String homeDrive) {
        this.homeDrive = homeDrive;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
