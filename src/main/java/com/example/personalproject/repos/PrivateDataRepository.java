package com.example.personalproject.repos;

import com.example.personalproject.models.ModelPrivateData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrivateDataRepository extends CrudRepository<ModelPrivateData, Long> {
    ModelPrivateData findByOmsNumber(String omsNumber);
    List<ModelPrivateData> findTopByOmsNumberOrderByIDPrivateDataDesc(String omsNumber);
}
