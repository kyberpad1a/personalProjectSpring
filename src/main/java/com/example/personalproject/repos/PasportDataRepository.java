package com.example.personalproject.repos;

import com.example.personalproject.models.ModelPasportData;
import com.example.personalproject.models.ModelPrivateData;
import com.example.personalproject.models.ModelUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PasportDataRepository extends CrudRepository<ModelPasportData,Long> {
    ModelPasportData findByPasportNumber(int pasportnumber);
    ModelPasportData findTopByPasportNumberOrderByIDPasportDesc(String pasportnumber);
}
