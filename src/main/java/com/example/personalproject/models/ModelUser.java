package com.example.personalproject.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Set;

@Entity
public class ModelUser {
    public ModelUser(){}
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long ID_User;
    @NotBlank
    @Size(max = 50, message = "Поле не может содержать более 50 символов")
    private String username;
    @NotBlank
    @Size(max = 100)
    private String password;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="privatedata_id")
    private ModelPrivateData privateData;
    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelQuality> qualities;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelShipment> modelShipmentCollection;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelMaintenance> modelMaintenanceCollection;

    public ModelUser(String username, String password, ModelPrivateData privateData, Set<RoleEnum> roles, Collection<ModelQuality> qualities, Collection<ModelShipment> modelShipmentCollection, Collection<ModelMaintenance> modelMaintenanceCollection) {

        this.username = username;
        this.password = password;
        this.privateData = privateData;
        this.roles = roles;
        this.qualities = qualities;
        this.modelShipmentCollection = modelShipmentCollection;
        this.modelMaintenanceCollection = modelMaintenanceCollection;
    }

    public Long getID_User() {
        return ID_User;
    }

    public void setID_User(Long ID_User) {
        this.ID_User = ID_User;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ModelPrivateData getPrivateData() {
        return privateData;
    }

    public void setPrivateData(ModelPrivateData privateData) {
        this.privateData = privateData;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public Collection<ModelQuality> getQualities() {
        return qualities;
    }

    public void setQualities(Collection<ModelQuality> qualities) {
        this.qualities = qualities;
    }

    public Collection<ModelShipment> getModelShipmentCollection() {
        return modelShipmentCollection;
    }

    public void setModelShipmentCollection(Collection<ModelShipment> modelShipmentCollection) {
        this.modelShipmentCollection = modelShipmentCollection;
    }

    public Collection<ModelMaintenance> getModelMaintenanceCollection() {
        return modelMaintenanceCollection;
    }

    public void setModelMaintenanceCollection(Collection<ModelMaintenance> modelMaintenanceCollection) {
        this.modelMaintenanceCollection = modelMaintenanceCollection;
    }
}
