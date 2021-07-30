package com.pragma.customer.domain.repository;

import com.pragma.customer.domain.dto.CityDto;
import com.pragma.customer.infrastructure.persistence.entity.CityEntity;

import java.util.Optional;

public interface CityRepositoryInterface {

    Optional<CityDto> findName(String name);

    void save(CityDto cityDto);

    Optional<CityEntity> renEntity(String name);
}
