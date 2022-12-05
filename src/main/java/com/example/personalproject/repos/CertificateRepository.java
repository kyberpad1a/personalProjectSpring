package com.example.personalproject.repos;

import com.example.personalproject.models.ModelCertificate;
import com.example.personalproject.models.ModelGood;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CertificateRepository extends CrudRepository<ModelCertificate, Long> {
    ModelCertificate findByCertificateName(String certificateName);
}
