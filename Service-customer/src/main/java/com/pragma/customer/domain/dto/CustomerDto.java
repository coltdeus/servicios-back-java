package com.pragma.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private IdentificationDto identificationDto;
    private CityDto cityDto;
}
