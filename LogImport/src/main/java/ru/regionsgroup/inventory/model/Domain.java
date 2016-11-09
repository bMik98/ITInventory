package ru.regionsgroup.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOMAINS")
public class Domain {
    @Id
    @JsonProperty
    @Column(name = "DNS_DOMAIN")
    private String dnsDomain;

    @JsonProperty
    @Column(name = "DISTINGUISHED_NAME")
    private String distingushedName;

    @JsonProperty
    @Column(name = "SHORT_NAME")
    private String shortName;

    public Domain() {
    }

    public String getDnsDomain() {
        return dnsDomain;
    }

    public void setDnsDomain(String dnsDomain) {
        this.dnsDomain = dnsDomain;
    }

    public String getDistingushedName() {
        return distingushedName;
    }

    public void setDistingushedName(String distingushedName) {
        this.distingushedName = distingushedName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
