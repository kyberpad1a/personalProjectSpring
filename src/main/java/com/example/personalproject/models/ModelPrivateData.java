package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class ModelPrivateData {
    public ModelPrivateData(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IDPrivateData;
    @NotBlank
    @Size(max = 35, message = "Поле не может содержать больше 35 символов")
    private String surname;
    @NotBlank
    @Size(max = 35, message = "Поле не может содержать больше 35 символов")
    private String name;
    @Size(max = 35, message = "Поле не может содержать больше 35 символов")
    private String patronymic;
    @Size(min = 16, max = 16,  message = "Полис ОМС состоит из 16 цифр")
    private String omsNumber;
    @OneToOne(optional = true)
    @JoinColumn(name="pasportdata_id")
    private ModelPasportData employeePasport;
    @OneToOne(optional = true, mappedBy = "privateData", cascade = CascadeType.ALL)
    private ModelUser owner;

    public ModelPrivateData(String surname, String name, String patronymic, String omsNumber, ModelPasportData employeePasport, ModelUser owner) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.omsNumber = omsNumber;
        this.employeePasport = employeePasport;
        this.owner = owner;
    }

    public Long getIDPrivateData() {
        return IDPrivateData;
    }

    public void setIDPrivateData(Long IDPrivateData) {
        this.IDPrivateData = IDPrivateData;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getOmsNumber() {
        return omsNumber;
    }

    public void setOmsNumber(String omsNumber) {
        this.omsNumber = omsNumber;
    }

    public ModelPasportData getEmployeePasport() {
        return employeePasport;
    }

    public void setEmployeePasport(ModelPasportData employeePasport) {
        this.employeePasport = employeePasport;
    }

    public ModelUser getOwner() {
        return owner;
    }

    public void setOwner(ModelUser owner) {
        this.owner = owner;
    }
}
