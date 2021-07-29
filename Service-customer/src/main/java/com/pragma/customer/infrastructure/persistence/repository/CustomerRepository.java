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

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerEntity> customerEntities = (List<CustomerEntity>)
                customerCrudRepository.findAll();
        return customerMapper.toCustomerDtos(customerEntities);
        //return customerCrudRepository.findAll();
    }

    @Override
    public Optional<List<CustomerDto>> ageGreaterThanOrEqual(Integer age) {
        //return Optional.empty();
        return customerCrudRepository.findByAgeGreaterThanEqual(age)
                .map(cust -> customerMapper.toCustomerDtos(cust));
    }

    @Override
    public void save(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.toCustomerEntity(customerDto);
        customerCrudRepository.save(customerEntity);
    }

    @Override
    public void delete(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.toCustomerEntity(customerDto);
        customerCrudRepository.delete(customerEntity);
    }

    @Override
    public Optional<CustomerDto> getCustomerId(Long id) {
        Optional<CustomerEntity> customerEntities = customerCrudRepository.findById(id);
        return customerEntities.map(cust -> customerMapper.toCustomerDto(cust));
    }

    @Override
    public Optional<CustomerDto> customerIdentification(Long id) {
        //Optional<CustomerEntity> customerEntity = customerCrudRepository.customerIdentification(id);
        Long customerId = customerCrudRepository.customerIdentification(id);
        Optional<CustomerEntity> customerEntity = customerCrudRepository.findById(customerId);
        //return Optional.empty();
        //return customerEntity.map(cust -> customerMapper.toCustomerDto(cust));
        return customerEntity.map(cus -> customerMapper.toCustomerDto(cus));
        //return customerMapper.toCustomerDto(customerEntity.get());
    }

    public Optional<CustomerDto> getCustomerIdentification(Long idIdentification) {
        Optional<CustomerEntity> customerEntities = customerCrudRepository
                .findById(idIdentification);
        return customerEntities.map(cust -> customerMapper.toCustomerDto(cust));
    }

}
