package com.pragma.customer.domain.repository;

import com.pragma.customer.domain.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryInterface {

    List<CustomerDto> getAll();

    Optional<List<CustomerDto>> ageGreaterThanOrEqual(Integer age);

    void save(CustomerDto customerDto);

    void delete(CustomerDto customerDto);

    public Optional<CustomerDto> getCustomerId(Long id);

    public Optional<CustomerDto> customerIdentification(Long id);

    //---opciones extras para facilitar el manejo---
    /**
    public CustomerEntity getCustomerType(IdentificationEntity identificationEntity);

    public CustomerEntity getCustomerCity(CityEntity cityEntity);

    public CustomerEntity getCustomerId(Long id);

    public CustomerEntity updateCliente(CustomerEntity customerEntity);
     */
}
