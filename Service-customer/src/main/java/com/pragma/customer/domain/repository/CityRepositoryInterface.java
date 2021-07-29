package com.pragma.customer.domain.repository;

import com.pragma.customer.domain.dto.CityDto;

import java.util.Optional;

public interface CityRepositoryInterface {

    Optional<CityDto> findName(String name);

    void save(CityDto cityDto);
}
