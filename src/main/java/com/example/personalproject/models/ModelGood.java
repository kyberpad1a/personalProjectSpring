package com.example.personalproject.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class ModelGood {
    public ModelGood(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Good;
    @NotNull
    @NotBlank(message = "Пустое поле")
    private String goodName;
    @NotNull
    @Positive(message = "Вес не может быть отрицательным")
    @Digits(integer = 3, fraction = 1, message = "Количество символов слева от запятой не может превышать 3, справа - 1")
    private double goodWeight;
    @ManyToOne(optional = true)
    private ModelCertificate certificate;
    @ManyToOne(optional = true)
    private ModelGoodType goodType;
    @ManyToOne(optional = true)
    private ModelMaterial material;
    @OneToMany(mappedBy = "good", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelShipment> modelShipmentCollection;
    @OneToMany(mappedBy = "good", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ModelQuality> modelQualityCollection;

    public ModelGood(String goodName, double goodWeight, ModelCertificate certificate, ModelGoodType goodType, ModelMaterial material, Collection<ModelShipment> modelShipmentCollection, Collection<ModelQuality> modelQualityCollection) {
        this.goodName = goodName;
        this.goodWeight = goodWeight;
        this.certificate = certificate;
        this.goodType = goodType;
        this.material = material;
        this.modelShipmentCollection = modelShipmentCollection;
        this.modelQualityCollection = modelQualityCollection;
    }

    public Long getID_Good() {
        return ID_Good;
    }

    public void setID_Good(Long ID_Good) {
        this.ID_Good = ID_Good;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodWeight() {
        return goodWeight;
    }

    public void setGoodWeight(double goodWeight) {
        this.goodWeight = goodWeight;
    }

    public ModelCertificate getCertificate() {
        return certificate;
    }

    public void setCertificate(ModelCertificate certificate) {
        this.certificate = certificate;
    }

    public ModelGoodType getGoodType() {
        return goodType;
    }

    public void setGoodType(ModelGoodType goodType) {
        this.goodType = goodType;
    }

    public ModelMaterial getMaterial() {
        return material;
    }

    public void setMaterial(ModelMaterial material) {
        this.material = material;
    }

    public Collection<ModelShipment> getModelShipmentCollection() {
        return modelShipmentCollection;
    }

    public void setModelShipmentCollection(Collection<ModelShipment> modelShipmentCollection) {
        this.modelShipmentCollection = modelShipmentCollection;
    }

    public Collection<ModelQuality> getModelQualityCollection() {
        return modelQualityCollection;
    }

    public void setModelQualityCollection(Collection<ModelQuality> modelQualityCollection) {
        this.modelQualityCollection = modelQualityCollection;
    }
}
