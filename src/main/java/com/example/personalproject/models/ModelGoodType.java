package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
public class ModelGoodType {
    public ModelGoodType(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_GoodType;
    @NotBlank(message = "Поле не может быть пустым")
    @Size(max = 50, message = "Длина поля не может превышать 50 символов")
    private String goodTypeName;
    @OneToMany(mappedBy = "goodType", fetch = FetchType.EAGER)
    private Collection<ModelGood> modelGoodCollection;

    public ModelGoodType(String goodTypeName, Collection<ModelGood> modelGoodCollection) {
        this.goodTypeName = goodTypeName;
        this.modelGoodCollection = modelGoodCollection;
    }

    public Long getID_GoodType() {
        return ID_GoodType;
    }

    public void setID_GoodType(Long ID_GoodType) {
        this.ID_GoodType = ID_GoodType;
    }

    public String getGoodTypeName() {
        return goodTypeName;
    }

    public void setGoodTypeName(String goodTypeName) {
        this.goodTypeName = goodTypeName;
    }

    public Collection<ModelGood> getModelGoodCollection() {
        return modelGoodCollection;
    }

    public void setModelGoodCollection(Collection<ModelGood> modelGoodCollection) {
        this.modelGoodCollection = modelGoodCollection;
    }
}
