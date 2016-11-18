package ru.regionsgroup.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mbedritskiy
 */
@Entity
@Table(name = "INSTALLED_SOFTWARE")
public class InstalledSoftware implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("reportDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "UPDATED")
    @Temporal(TemporalType.DATE)
    private Date updated;

    @JsonProperty
    @Column(name = "COMPUTER_ID")
    private String computerId;

    @JsonProperty
    @Column(name = "REGISTRY_KEY")
    private String registryKey;

    @JsonProperty
    @Column(name = "TITLE")
    private String title;

    @JsonProperty
    @Column(name = "VERSION")
    private String version;

    @JsonProperty
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "INSTALLED")
//    @Temporal(TemporalType.DATE)
    private String installed;

    @JsonProperty
    @Column(name = "PUBLISHER")
    private String publisher;

    @ManyToOne(targetEntity = Computer.class)
//    @JoinColumn(name = "COMPUTER_ID",
//            foreignKey = @ForeignKey(name = "COMPUTER_ID"),
//            insertable = false,
//            updatable = false)
    private Computer computer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getComputerId() {
        return computerId;
    }

    public void setComputerId(String computerId) {
        this.computerId = computerId;
    }

    public String getRegistryKey() {
        return registryKey;
    }

    public void setRegistryKey(String registryKey) {
        this.registryKey = registryKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstalled() {
        return installed;
    }

    public void setInstalled(String installed) {
        this.installed = installed;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
