package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.sql.Date;
import java.util.Collection;

@Entity
public class ModelCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Certificate;
    @NotBlank(message = "Поле не может быть пустым")
    private String certificateName;
    @NotNull(message = "Дата не может быть пустой")
    private Date expirationDate;
    @OneToMany(mappedBy = "certificate", fetch = FetchType.EAGER)
    private Collection<ModelGood> modelGood;


    public ModelCertificate() {

    }

    public ModelCertificate(String certificateName, Date expirationDate, Collection<ModelGood> modelGood) {
        this.certificateName = certificateName;
        this.expirationDate = expirationDate;
        this.modelGood = modelGood;


    }

    public Long getID_Certificate() {
        return ID_Certificate;
    }

    public void setID_Certificate(Long ID_Certificate) {
        this.ID_Certificate = ID_Certificate;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Collection<ModelGood> getModelGood() {
        return modelGood;
    }

    public void setModelGood(Collection<ModelGood> modelGood) {
        this.modelGood = modelGood;
    }
}


