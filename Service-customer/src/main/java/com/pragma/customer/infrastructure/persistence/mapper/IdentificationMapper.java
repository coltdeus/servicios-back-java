package com.pragma.customer.infrastructure.persistence.mapper;

import com.pragma.customer.domain.dto.IdentificationDto;
import com.pragma.customer.infrastructure.persistence.entity.IdentificationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IdentificationMapper {
    @Mappings({
            //@Mapping(source = "", target = "")
            @Mapping(source = "number", target = "document")
    })
    IdentificationDto toIdentificationDto(IdentificationEntity identificationEntity);

    @InheritInverseConfiguration
    //@Mapping(target = "customerEntity", ignore = true)
    IdentificationEntity toIdentificationEntity(IdentificationDto identificationDto);
}
