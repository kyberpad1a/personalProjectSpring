package com.example.personalproject.repos;


import com.example.personalproject.models.ModelGood;
import com.example.personalproject.models.ModelGoodType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodTypeRepository extends CrudRepository<ModelGoodType, Long> {
    ModelGoodType findByGoodTypeName(String goodTypeName);
}
