package com.pragma.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class IdentificationDto {

    private Long id;
    private String type;
    private Integer document;
}
