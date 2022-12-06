package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
public class ModelMaterial {
    public ModelMaterial(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Material;
    @NotBlank
    @Size(max = 40, message = "Длина поля не может превышать 40 символов")
    private String materialName;
    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER)
    private Collection<ModelGood> modelGood;

    public ModelMaterial(String materialName, Collection<ModelGood> modelGood) {
        this.materialName = materialName;
        this.modelGood = modelGood;
    }

    public Long getID_Material() {
        return ID_Material;
    }

    public void setID_Material(Long ID_Material) {
        this.ID_Material = ID_Material;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Collection<ModelGood> getModelGood() {
        return modelGood;
    }

    public void setModelGood(Collection<ModelGood> modelGood) {
        this.modelGood = modelGood;
    }
}
