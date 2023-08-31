package com.krakozhia.visa.visaApplication.infrastacture.jpa;

import com.krakozhia.visa.visaApplication.infrastacture.jpa.entity.AddressEntity;
import com.krakozhia.visa.visaApplication.infrastacture.jpa.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
}

