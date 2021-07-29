package com.pragma.customer.infrastructure.persistence.mapper;

import com.pragma.customer.domain.dto.CityDto;
import com.pragma.customer.infrastructure.persistence.entity.CityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDto toCityDto(CityEntity cityEntity);

    @InheritInverseConfiguration
    CityEntity toCityEntity(CityDto cityDto);
}
