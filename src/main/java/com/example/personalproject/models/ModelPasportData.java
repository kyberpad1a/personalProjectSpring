package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Entity
public class ModelPasportData {
    public ModelPasportData(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IDPasport;
    @NotNull
    @Min(value = 6, message = "Номер должен содержать 6 цифр")
    //@Max(value = 6, message = "Номер должен содержать 6 цифр")
    private int pasportNumber;
    @NotNull
    @Min(value = 4, message = "Серия должна содержать 4 цифры")
    //@Max(value = 4, message = "Серия должна содержать 4 цифры")
    private int pasportSeries;
    @OneToOne(optional = true, mappedBy = "employeePasport", cascade = CascadeType.ALL)
    private ModelPrivateData owner;

    public ModelPasportData(int pasportNumber, int pasportSeries) {
        this.pasportNumber = pasportNumber;
        this.pasportSeries = pasportSeries;
        this.owner = owner;
    }

    public Long getIDPasport() {
        return IDPasport;
    }

    public void setIDPasport(Long IDPasport) {
        this.IDPasport = IDPasport;
    }

    public int getPasportNumber() {
        return pasportNumber;
    }

    public void setPasportNumber(int pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public int getPasportSeries() {
        return pasportSeries;
    }

    public void setPasportSeries(int pasportSeries) {
        this.pasportSeries = pasportSeries;
    }

    public ModelPrivateData getOwner() {
        return owner;
    }

    public void setOwner(ModelPrivateData owner) {
        this.owner = owner;
    }
}
