package ru.regionsgroup.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.regionsgroup.inventory.model.format.BooleanDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PRINTER_CONNECTIONS")
public class PrinterConnection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("ReportDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "UPDATED")
    @Temporal(TemporalType.DATE)
    private Date updated;

    @JsonProperty("ComputerID")
    @Column(name = "COMPUTER_ID")
    private String computerId;

    @JsonProperty("ComputerName")
    @Column(name = "COMPUTER_NAME")
    private String computerName;

    @JsonProperty("ComputerDnsDomain")
    @Column(name = "COMPUTER_DNS_DOMAIN")
    private String computerDnsDomain;

    @JsonProperty("ComputerDN")
    @Column(name = "COMPUTER_DISTINGUISHED_NAME")
    private String computerDistinguishName;

    @JsonProperty("Site")
    @Column(name = "SITE")
    private String site;

    @JsonProperty("Name")
    @Column(name = "NAME")
    private String name;

    @JsonProperty("Caption")
    @Column(name = "CAPTION")
    private String caption;

    @JsonProperty("DriverName")
    @Column(name = "DRIVER_NAME")
    private String driverName;

    @JsonProperty("PortName")
    @Column(name = "PORT_NAME")
    private String portName;

    @JsonProperty("Default")
    @JsonDeserialize(using = BooleanDeserializer.class)
    @Column(name = "AS_DEFAULT")
    private boolean asDefault;

    @JsonProperty("ServerName")
    @Column(name = "SERVER_NAME")
    private String serverName;

    @JsonProperty("Comment")
    @Column(name = "COMMENT")
    private String comment;

    @JsonProperty("Location")
    @Column(name = "LOCATION")
    private String location;

    @ManyToOne
    @JoinColumn(name = "COMPUTER_ID", insertable = false, updatable = false)
    private Computer computer;

    public void setComputer(Computer computer) {
        this.computer = computer;
        this.computer.addPrinterConnection(this);
    }

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

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getComputerDnsDomain() {
        return computerDnsDomain;
    }

    public void setComputerDnsDomain(String computerDnsDomain) {
        this.computerDnsDomain = computerDnsDomain;
    }

    public String getComputerDistinguishName() {
        return computerDistinguishName;
    }

    public void setComputerDistinguishName(String computerDistinguishName) {
        this.computerDistinguishName = computerDistinguishName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public boolean isAsDefault() {
        return asDefault;
    }

    public void setAsDefault(boolean asDefault) {
        this.asDefault = asDefault;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Computer getComputer() {
        return computer;
    }
}
