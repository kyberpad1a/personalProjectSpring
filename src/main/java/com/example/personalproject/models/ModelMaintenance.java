package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class ModelMaintenance {
    public ModelMaintenance(){}
    @Id
    @GeneratedValue
    private Long ID_Maintenance;
    @NotNull
    private Date maintenanceDate;
    @NotBlank
    @Size(max = 200, message = "Длина поля не может превышать 200 символов")
    private String maintenanceText;
    private boolean maintenanceStatus;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ModelUser user;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ModelWarehouse warehouse;

    public ModelMaintenance(Date maintenanceDate, String maintenanceText, boolean maintenanceStatus, ModelUser user, ModelWarehouse warehouse) {

        this.maintenanceDate = maintenanceDate;
        this.maintenanceText = maintenanceText;
        this.maintenanceStatus = maintenanceStatus;
        this.user = user;
        this.warehouse = warehouse;
    }

    public Long getID_Maintenance() {
        return ID_Maintenance;
    }

    public void setID_Maintenance(Long ID_Maintenance) {
        this.ID_Maintenance = ID_Maintenance;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceText() {
        return maintenanceText;
    }

    public void setMaintenanceText(String maintenanceText) {
        this.maintenanceText = maintenanceText;
    }

    public boolean isMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(boolean maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public ModelWarehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(ModelWarehouse warehouse) {
        this.warehouse = warehouse;
    }
}
