package com.pragma.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private IdentificationDto identificationDto;
    private CityDto cityDto;
    //---CONEXION FOTO---
    private String foto;
}
