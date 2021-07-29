package com.pragma.customer.infrastructure.persistence.crud;

import com.pragma.customer.infrastructure.persistence.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityCrudRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> findByName (String name);
}
