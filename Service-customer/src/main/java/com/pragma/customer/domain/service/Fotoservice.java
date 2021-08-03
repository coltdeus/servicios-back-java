package com.pragma.customer.domain.service;

import com.pragma.customer.application.utils.ErrorMessage;
import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.domain.dto.FotoDto;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
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
import java.util.stream.Collectors;

@Service
public class FotoService {

    private final Logger log = LoggerFactory.getLogger(FotoService.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private FotoRest fotoRest;

    public CustomerDto getCustomer(CustomerEntity customerEntity){
        ResponseEntity<FotoDto> fotoResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            fotoResponseEntity = fotoRest.getCustomerId(customerEntity.getId());
        } catch (Exception exception) {
            log.error(exception.toString());
        }
        FotoDto fotoDto;
        if (fotoResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            fotoDto = fotoResponseEntity.getBody();
        } else {
            fotoDto = FotoDto.builder().foto(ErrorMessage.NOT_FOUND_FOTO).build();
        }
        return customerMapper.toCustomerDto(customerEntity, fotoDto);
    }

    public List<CustomerDto> getCustomers (List<CustomerEntity> customerEntities) throws NotFoundException {
        List<CustomerDto> customers = new ArrayList<>();
        //---LISTA DE FOTOS DE SERVICIO---
        List<Long> idCustomers = customerEntities.stream()
                .map(CustomerEntity::getId)
                .collect(Collectors.toList());
        ResponseEntity<List<FotoDto>> fotoResponseEntity = fotoRest.getCustomerIds(idCustomers);
        //---MAP---
        Map<Long, String> fotos = new HashMap<>();
        if (fotoResponseEntity.getStatusCode().is2xxSuccessful()) {
            fotos = fotoResponseEntity.getBody()
                    .stream()
                    .collect(Collectors.toMap(FotoDto::getCustomerId, FotoDto::getFoto));
        }
        //---CREACION DE CUSTOMER---
        FotoDto fotoDto = FotoDto.builder().build();
        for (CustomerEntity customerEntity : customerEntities) {
            fotoDto.setFoto(fotos.getOrDefault(customerEntity.getId(), ErrorMessage.NOT_FOUND_FOTO));
            customers.add(customerMapper.toCustomerDto(customerEntity, fotoDto));
        }
        return customers;
    }
}
