package com.pragma.customer.domain.service;

import com.pragma.customer.application.utils.ErrorMessage;
import com.pragma.customer.domain.dto.IdentificationDto;
import com.pragma.customer.domain.repository.IdentificationRepositoryInterface;
import com.pragma.customer.infrastructure.persistence.mapper.IdentificationMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentificationService {

    @Autowired
    private IdentificationRepositoryInterface identificationRepositoryInterface;

    @Autowired
    private IdentificationMapper identificationMapper;

    public IdentificationDto findByNumberAndType(Integer number, String type) throws NotFoundException {
        Optional<IdentificationDto> identificationDto = identificationRepositoryInterface
                .findNumberAndType(number, type);
        if(identificationDto.isEmpty()){
            throw new NotFoundException(ErrorMessage.identificacionNoRegistrada(type, number));
        }
        return identificationDto.get();
    }

    public boolean exist(String type, Integer number){
        Optional<IdentificationDto> identificationDto = identificationRepositoryInterface
                .findNumberAndType(number, type);
        return identificationDto.isPresent();
    }

    public IdentificationDto save (String type, Integer number){
        if(exist(type, number)){
            throw new IllegalArgumentException("Ya se encuentra Registado (F)");
        }
        IdentificationDto identificationDto = IdentificationDto.builder()
                .type(type)
                .document(number)
                .build();
        identificationRepositoryInterface.save(identificationDto);
        return identificationDto;
    }

    public void delete(IdentificationDto identificationDto){
        identificationRepositoryInterface.delete(identificationDto);
    }

    /**
    public IdentificationEntity findByNumberAndType(Integer numero, String type) throws NotFoundException {
        Optional<IdentificationEntity> identificationEntity = identificationRepositoryInterface.findNumberAndType(numero, type);
        if(identificationEntity.isEmpty()){
            throw new NotFoundException("No se encontro la identificacion con "+numero+" de tipo "+type);
        }
        return identificationEntity.get();
    }

    public boolean exist(String type, Integer number){
        Optional<IdentificationEntity> identificationEntity = identificationRepositoryInterface.findNumberAndType(number, type);
        return identificationEntity.isPresent();
    }

    public IdentificationEntity save (CustomerEntity customerEntity, String type, Integer number){
        if(exist(type, number)){
            throw new IllegalArgumentException("Ya se encuentra Registado (F)");
        }
        IdentificationEntity identificationEntity = IdentificationEntity.builder()
                .type(type)
                .number(number)
                .customerEntity(customerEntity)
                .build();
        identificationRepositoryInterface.save(identificationEntity);
        return identificationEntity;
    }

    public void delete(IdentificationEntity identificationEntity){
        identificationRepositoryInterface.delete(identificationEntity);
    }
    */
}
