package com.pragma.customer.domain.repository;

import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryInterface {

    List<CustomerEntity> getAll();

    Optional<List<CustomerEntity>> ageGreaterThanOrEqual(Integer age);

    void save(CustomerEntity customerEntity);

    void delete(CustomerEntity customerEntity);

    Optional<CustomerEntity> getCustomerId(Long id);

    Optional<CustomerEntity> customerIdentification(Long id);

    //---opciones extras para facilitar el manejo---
    /**
    public CustomerEntity getCustomerType(IdentificationEntity identificationEntity);

    public CustomerEntity getCustomerCity(CityEntity cityEntity);

    public CustomerEntity getCustomerId(Long id);

    public CustomerEntity updateCliente(CustomerEntity customerEntity);
     */
}
