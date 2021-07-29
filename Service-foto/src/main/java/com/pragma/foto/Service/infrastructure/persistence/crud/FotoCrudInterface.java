package com.pragma.foto.Service.infrastructure.persistence.crud;

import com.pragma.foto.Service.infrastructure.persistence.entity.FotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FotoCrudInterface extends MongoRepository<FotoEntity, String> {
    Optional<FotoEntity> findByCustomerId(Long cuatomerId);
}
