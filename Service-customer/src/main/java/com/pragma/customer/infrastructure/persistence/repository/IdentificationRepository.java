package com.pragma.customer.infrastructure.persistence.repository;

import com.pragma.customer.domain.dto.IdentificationDto;
import com.pragma.customer.domain.repository.IdentificationRepositoryInterface;
import com.pragma.customer.infrastructure.persistence.crud.IdentificationCrudRepository;
import com.pragma.customer.infrastructure.persistence.entity.CityEntity;
import com.pragma.customer.infrastructure.persistence.entity.IdentificationEntity;
import com.pragma.customer.infrastructure.persistence.mapper.IdentificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IdentificationRepository implements IdentificationRepositoryInterface {

    @Autowired
    private IdentificationMapper identificationMapper;

    @Autowired
    private IdentificationCrudRepository identificationCrudRepository;

    @Override
    public Optional<IdentificationDto> findNumberAndType(Integer document, String tipo) {
        Optional<IdentificationEntity> identificationEntity = identificationCrudRepository
                .findByNumberAndType(document, tipo);
        return identificationEntity
                .map(iden -> identificationMapper.toIdentificationDto(iden));
        //return Optional.empty();
    }

    @Override
    public void save(IdentificationDto identificationDto) {
        IdentificationEntity identificationEntity = identificationMapper
                .toIdentificationEntity(identificationDto);
        identificationCrudRepository.save(identificationEntity);
    }

    @Override
    public void delete(IdentificationDto identificationDto) {
        try {
            IdentificationEntity identificationEntity = identificationMapper
                    .toIdentificationEntity(identificationDto);
            identificationCrudRepository.delete(identificationEntity);
        } catch (Exception e){
            System.out.println("se daño" + e);
        }
    }

    public Optional<IdentificationEntity> renEntity(Integer number, String Type){
        Optional<IdentificationEntity> identificationEntity = identificationCrudRepository.findByNumberAndType(number, Type);
        return  identificationEntity;
    }
}
