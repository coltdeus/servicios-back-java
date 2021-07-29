package com.pragma.customer.infrastructure.persistence.crud;

import com.pragma.customer.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


//-------------------------------------DAO------------------------------------------------------------------------------
public interface CustomerCrudRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<List<CustomerEntity>> findByAgeGreaterThanEqual(Integer age);

    @Query(nativeQuery = true,value = "SELECT id FROM customers WHERE identification_id = :id")
    Long customerIdentification(@Param("id")Long id);
}
