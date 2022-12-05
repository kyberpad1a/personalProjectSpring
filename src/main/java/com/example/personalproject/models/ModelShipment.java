package com.example.personalproject.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class ModelShipment {
    public ModelShipment(){}
    @Id
    @GeneratedValue
    private Long ID_Shipment;
    private Date shipmentDate;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ModelGood good;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ModelWarehouse warehouse;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ModelUser user;

    public ModelShipment(Date shipmentDate, ModelGood good, ModelWarehouse warehouse, ModelUser user) {
        this.shipmentDate = shipmentDate;
        this.good = good;
        this.warehouse = warehouse;
        this.user = user;
    }

    public Long getID_Shipment() {
        return ID_Shipment;
    }

    public void setID_Shipment(Long ID_Shipment) {
        this.ID_Shipment = ID_Shipment;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public ModelGood getGood() {
        return good;
    }

    public void setGood(ModelGood good) {
        this.good = good;
    }

    public ModelWarehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(ModelWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }
}
