package com.example.personalproject.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class ModelWarehouse {
    public ModelWarehouse(){}
    @Id
    @GeneratedValue
    private Long ID_Warehouse;
    @NotBlank
    private String warehouseAddress;
    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelShipment> shipments;
    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelMaintenance> maintenances;

    public ModelWarehouse(String warehouseAddress, Collection<ModelShipment> shipments, Collection<ModelMaintenance> maintenances) {
        this.warehouseAddress = warehouseAddress;
        this.shipments = shipments;
        this.maintenances = maintenances;
    }

    public Long getID_Warehouse() {
        return ID_Warehouse;
    }

    public void setID_Warehouse(Long ID_Warehouse) {
        this.ID_Warehouse = ID_Warehouse;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Collection<ModelShipment> getShipments() {
        return shipments;
    }

    public void setShipments(Collection<ModelShipment> shipments) {
        this.shipments = shipments;
    }

    public Collection<ModelMaintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(Collection<ModelMaintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
