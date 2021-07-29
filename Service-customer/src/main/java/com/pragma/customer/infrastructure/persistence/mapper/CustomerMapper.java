package com.pragma.customer.infrastructure.persistence.mapper;

import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IdentificationMapper.class, CityMapper.class})
public interface CustomerMapper {
    @Mappings({
//      @Mapping(source = "", target = "")
      @Mapping(source = "cityEntity", target = "cityDto"),
      @Mapping(source = "identificationEntity", target = "identificationDto")
    })
    CustomerDto toCustomerDto(CustomerEntity customerEntity);
    List<CustomerDto> toCustomerDtos(List<CustomerEntity> customerEntityList);

    @InheritInverseConfiguration
    CustomerEntity toCustomerEntity(CustomerDto customerDto);

}
