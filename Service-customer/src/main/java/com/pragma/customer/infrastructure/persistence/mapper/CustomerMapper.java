package com.pragma.customer.infrastructure.persistence.mapper;

import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.domain.dto.FotoDto;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

//, uses = {IdentificationMapper.class, CityMapper.class}
//@MapperConfig(componentModel = "spring")
@Mapper(componentModel = "spring", uses = {IdentificationMapper.class, CityMapper.class})
public interface CustomerMapper {

    @Mappings({
//      @Mapping(source = "", target = "")
            @Mapping(source = "customerEntity.cityEntity", target = "cityDto"),
            @Mapping(source = "customerEntity.identificationEntity", target = "identificationDto"),
            @Mapping(source = "fotoDto."+FotoDto.Atributos.FOTO, target = "foto")
    })
    //, FotoDto fotoEntity
    CustomerDto toCustomerDto(CustomerEntity customerEntity, FotoDto fotoDto);
    //, List<FotoDto> fotoDtos
    //List<CustomerDto> toCustomerDtos(List<CustomerEntity> customerEntityList, List<FotoDto> fotoDtos);

    //@InheritInverseConfiguration
    //CustomerEntity toCustomerEntity(CustomerDto customerDto);

}
