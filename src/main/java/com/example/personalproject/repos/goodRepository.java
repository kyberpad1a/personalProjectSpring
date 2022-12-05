package com.example.personalproject.repos;

import com.example.personalproject.models.ModelGood;
import com.example.personalproject.models.ModelUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface goodRepository extends CrudRepository<ModelGood, Long> {
    List<ModelGood> findByGoodNameContains(String goodName);
    ModelGood findByGoodName(String goodName);
}
