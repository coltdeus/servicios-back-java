package com.pragma.customer.infrastructure.persistence.crud;

import com.pragma.customer.infrastructure.persistence.entity.IdentificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentificationCrudRepository extends JpaRepository<IdentificationEntity, Long> {

    Optional<IdentificationEntity> findById(Long id);

    Optional<IdentificationEntity> findByNumberAndType(Integer number, String type);
}
