package com.pragma.customer.infrastructure.persistence.repository;

import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.domain.repository.CustomerRepositoryInterface;
import com.pragma.customer.infrastructure.persistence.crud.CustomerCrudRepository;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
import com.pragma.customer.infrastructure.persistence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository implements  CustomerRepositoryInterface {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerCrudRepository customerCrudRepository;
//---TODOS LOS CUSTOMERS---
    @Override
    public List<CustomerEntity> getAll() {
        return (List<CustomerEntity>) customerCrudRepository.findAll();
    }
//---ENCONTRAR CUSTOMER---
    @Override
    public Optional<List<CustomerEntity>> ageGreaterThanOrEqual(Integer age) {
        return customerCrudRepository.findByAgeGreaterThanEqual(age);
    }
//---GUARDAR CUSTOMER---
    @Override
    public void save(CustomerEntity customerEntity) {
        customerCrudRepository.save(customerEntity);
    }
///---ELIMINAR CUSTOMER---
    @Override
    public void delete(CustomerEntity customerEntity) {
        customerCrudRepository.delete(customerEntity);
    }
//---CUSTOMER DE SU ID---
    @Override
    public Optional<CustomerEntity> getCustomerId(Long id) {
        return customerCrudRepository.findById(id);
    }
//---CUSTOMER CON EL ID DE SU IDENTIFICACION CORRESPONDIENTE---
    @Override
    public Optional<CustomerEntity> customerIdentification(Long id) {
        Long customerId = customerCrudRepository.customerIdentification(id);
        Optional<CustomerEntity> customerEntity = customerCrudRepository.findById(customerId);
        return customerEntity;
    }

}
