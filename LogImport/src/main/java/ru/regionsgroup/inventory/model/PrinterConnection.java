package ru.regionsgroup.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.*;
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

    @JsonProperty("reportDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "UPDATED")
    @Temporal(TemporalType.DATE)
    private Date updated;

    @JsonProperty
    @Column(name = "COMPUTER_ID")
    private String computerId;

    @JsonProperty
    @Column(name = "NAME")
    private String name;

    @JsonProperty
    @Column(name = "CAPTION")
    private String caption;

    @JsonProperty
    @Column(name = "DRIVER_NAME")
    private String driverName;

    @JsonProperty
    @Column(name = "PORT_NAME")
    private String portName;

    @JsonProperty
//    @JsonDeserialize(using = BooleanDeserializer.class)
    @Column(name = "AS_DEFAULT")
    private boolean asDefault;

    @JsonProperty
    @Column(name = "SERVER_NAME")
    private String serverName;

    @ManyToOne
    @JoinColumn(name = "COMPUTER_ID", insertable = false, updatable = false)
    private Computer computer;

    public void setComputer(Computer computer) {
        this.computer = computer;
        this.computer.addPrinterConnection(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PrinterConnection)) {
            return false;
        }
        PrinterConnection that = (PrinterConnection) object;
        return new EqualsBuilder()
                .append(computerId, that.computerId)
                .append(name, that.name)
                .append(caption, that.caption)
                .append(driverName, that.driverName)
                .append(portName, that.portName)
                .append(serverName, that.serverName)
                .append(asDefault, that.asDefault)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(computerId)
                .append(name)
                .append(caption)
                .append(driverName)
                .append(portName)
                .append(serverName)
                .append(asDefault)
                .toHashCode();
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

    public Computer getComputer() {
        return computer;
    }
}
