package com.example.personalproject.repos;

import com.example.personalproject.models.ModelUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ModelUser,Long> {
    ModelUser findByUsername(String username);
}
