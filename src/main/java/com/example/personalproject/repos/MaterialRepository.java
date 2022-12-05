package com.example.personalproject.repos;

import com.example.personalproject.models.ModelGood;
import com.example.personalproject.models.ModelMaterial;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialRepository extends CrudRepository<ModelMaterial, Long> {
    ModelMaterial findByMaterialName(String materialName);
}
