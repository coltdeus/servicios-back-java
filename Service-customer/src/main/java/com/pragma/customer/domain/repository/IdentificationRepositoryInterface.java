package com.pragma.customer.domain.repository;

import com.pragma.customer.domain.dto.IdentificationDto;
import com.pragma.customer.infrastructure.persistence.entity.IdentificationEntity;

import java.util.Optional;

public interface IdentificationRepositoryInterface {

    Optional<IdentificationDto> findNumberAndType (Integer document, String tipo);

    void save(IdentificationDto identificationDto);

    void delete(IdentificationDto identificationDto);

    Optional<IdentificationEntity> renEntity(Integer number, String Type);
}
