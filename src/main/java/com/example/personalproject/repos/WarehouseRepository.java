package com.example.personalproject.repos;

import com.example.personalproject.models.ModelUser;
import com.example.personalproject.models.ModelWarehouse;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<ModelWarehouse, Long> {
    ModelWarehouse findByWarehouseAddress(String warehouseAddress);
}
