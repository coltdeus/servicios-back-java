package com.pragma.customer.domain.service;

import com.pragma.customer.application.utils.ErrorMessage;
import com.pragma.customer.domain.dto.CityDto;
import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.domain.dto.FotoDto;
import com.pragma.customer.domain.dto.IdentificationDto;
import com.pragma.customer.domain.repository.CustomerRepositoryInterface;
import com.pragma.customer.infrastructure.persistence.entity.CityEntity;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
import com.pragma.customer.infrastructure.persistence.entity.IdentificationEntity;
import com.pragma.customer.infrastructure.persistence.mapper.CustomerMapper;
import com.pragma.customer.infrastructure.rest.FotoRest;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepositoryInterface customerRepositoryInterface;

    @Autowired
    private CityService cityService;

    @Autowired
    private IdentificationService identificationService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private FotoRest fotoRest;

    @Autowired
    private FotoService fotoService;

    public CustomerDto findByIdentification(String type, Integer number) throws NotFoundException {
        IdentificationDto identificationDto = identificationService.findByNumberAndType(number, type);
        Optional<CustomerEntity> myCustomer = customerRepositoryInterface.customerIdentification(identificationDto.getId());
        return fotoService.getCustomer(myCustomer.get());
        //return myCustomer.get();
    }

    public List<CustomerDto> getAll() throws NotFoundException {
        List<CustomerEntity> customerEntities = customerRepositoryInterface.getAll();
        return fotoService.getCustomers(customerEntities);
    }

    public List<CustomerDto> findByAge(Integer age) throws NotFoundException {
        Optional<List<CustomerEntity>> customerEntities = customerRepositoryInterface.ageGreaterThanOrEqual(age);
        if (customerEntities.isEmpty()){
            throw new NotFoundException(ErrorMessage.sinClientesPorEdad(age));
        }
        return fotoService.getCustomers(customerEntities.get());
    }

    public void save(CustomerDto customerDto) throws NotFoundException {
        if(identificationService.exist(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument())
        ){
            //throw new IllegalArgumentException("El Customer ya se encuentra creado(SAVE)");
            throw new IllegalArgumentException(ErrorMessage.identificacionYaRegistrada(
                    customerDto.getIdentificationDto().getType(),
                    customerDto.getIdentificationDto().getDocument())
            );
        }
        //---CIUDAD---
        CityDto cityDto = cityService.save(customerDto.getCityDto().getName());
        //---IDENTIFICATION---
        IdentificationDto identificationDto = identificationService.save(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument()
        );
        //---CUSTOMER---
        CityEntity cityEntity = cityService.renEntity(customerDto.getCityDto().getName());
        IdentificationEntity identificationEntity = identificationService.renEntity(identificationDto.getDocument(), identificationDto.getType());
        CustomerEntity customerEntity = CustomerEntity.builder()
                .age(customerDto.getAge())
                .lastName(customerDto.getLastName())
                .name(customerDto.getName())
                .cityEntity(cityEntity)
                .identificationEntity(identificationEntity)
                .build();
        customerRepositoryInterface.save(customerEntity);
        //---FOTO---
        FotoDto fotoDto = FotoDto.builder()
                .customerId(customerEntity.getId())
                .foto(customerDto.getFoto())
                .build();
        fotoRest.save(fotoDto);
    }

    public void update(CustomerDto customerDto) throws NotFoundException {
        if(!identificationService.exist(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument())
        ){
            throw new NotFoundException(ErrorMessage.identificacionNoRegistrada(
                    customerDto.getIdentificationDto().getType(),
                    customerDto.getIdentificationDto().getDocument())
            );
        }
        //---CIUDAD---
        CityDto cityDto = cityService.save(customerDto.getCityDto().getName());
        //---IDENTIFICACION---
        IdentificationDto identificationDto = identificationService.findByNumberAndType(
                customerDto.getIdentificationDto().getDocument(),
                customerDto.getIdentificationDto().getType()
        );
        //---CUSTOMER---
        CityEntity cityEntity = cityService.renEntity(customerDto.getCityDto().getName());
        IdentificationEntity identificationEntity = identificationService.renEntity(identificationDto.getDocument(), identificationDto.getType());
        Optional<CustomerEntity> myCustomer = customerRepositoryInterface.customerIdentification(identificationDto.getId());
        myCustomer.get().setAge(customerDto.getAge());
        myCustomer.get().setLastName(customerDto.getLastName());
        myCustomer.get().setName(customerDto.getName());
        myCustomer.get().setCityEntity(cityEntity);
        myCustomer.get().setIdentificationEntity(identificationEntity);
        customerRepositoryInterface.save(myCustomer.get());
        //---FOTO---
        FotoDto fotoDto = FotoDto.builder()
                .customerId(myCustomer.get().getId())
                .foto(customerDto.getFoto())
                .build();
        fotoRest.save(fotoDto);
    }

    public void deleteCustomer(CustomerDto customerDto) throws NotFoundException {
        if(!identificationService.exist(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument())
        ){
            throw new IllegalArgumentException(ErrorMessage.identificacionNoRegistrada(
                    customerDto.getIdentificationDto().getType(),
                    customerDto.getIdentificationDto().getDocument())
            );
        }
        IdentificationDto identificationDto = identificationService.findByNumberAndType(
                customerDto.getIdentificationDto().getDocument(),
                customerDto.getIdentificationDto().getType()
        );
        Optional<CustomerEntity> myCustomer = customerRepositoryInterface.customerIdentification(
                identificationDto.getId());
        customerRepositoryInterface.delete(myCustomer.get());
        identificationService.delete(identificationDto);
        fotoRest.delete(myCustomer.get().getId());
    }

    public void delete(String type, Integer document) throws NotFoundException {
        if (!identificationService.exist(type, document)) {
            throw new IllegalArgumentException(ErrorMessage.identificacionNoRegistrada(type, document));
        }
        IdentificationDto identificationDto = identificationService.findByNumberAndType(document, type);
        Optional<CustomerEntity> myCustomer = customerRepositoryInterface.customerIdentification(identificationDto.getId());
        customerRepositoryInterface.delete(myCustomer.get());
        fotoRest.delete(myCustomer.get().getId());
        identificationService.delete(identificationDto);
    }
}