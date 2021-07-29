package com.pragma.customer.domain.repository;

import com.pragma.customer.domain.dto.IdentificationDto;

import java.util.Optional;

public interface IdentificationRepositoryInterface {

    Optional<IdentificationDto> findNumberAndType (Integer document, String tipo);

    void save(IdentificationDto identificationDto);

    void delete(IdentificationDto identificationDto);
}
