package com.pragma.customer;

import com.pragma.customer.infrastructure.persistence.crud.CustomerCrudRepository;
import com.pragma.customer.infrastructure.persistence.crud.IdentificationCrudRepository;
import com.pragma.customer.infrastructure.persistence.entity.CityEntity;
import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
import com.pragma.customer.infrastructure.persistence.entity.IdentificationEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CustomerRepositoryMockTest {

    @Autowired
    private CustomerCrudRepository customerCrudRepository;

    @Autowired
    private IdentificationCrudRepository identificationCrudRepository;

    @Test
    public void whenFindAge_thenResturnListCustomers(){
        IdentificationEntity identificationPrueba1 = IdentificationEntity.builder()
                //.id(4L)
                .number(60404116)
                .type("CC")
                .build();
        identificationCrudRepository.save(identificationPrueba1);
        //CityEntity cityPrueba1 = CityEntity.builder().id(1L).build();
        CustomerEntity customerPrueba1 = CustomerEntity.builder()
                .age(40)
                .lastName("rodriguez")
                .name("tsade")
                .cityEntity(CityEntity.builder().id(1L).build())
                .identificationEntity(identificationPrueba1)
                .build();
        customerCrudRepository.save(customerPrueba1);
        //IdentificationEntity.builder().id(4L).build()
        //Optional<List<CustomerEntity>> founds = customerCrudRepository.findByAgeGreaterThanEqual(18);
        Optional<List<CustomerEntity>> founds = customerCrudRepository.findByAgeGreaterThanEqual(18);
        Assertions.assertThat(founds.get().size()).isEqualTo(5);
    }
}
