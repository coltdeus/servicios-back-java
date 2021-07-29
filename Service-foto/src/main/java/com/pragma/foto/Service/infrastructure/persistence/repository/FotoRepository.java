package com.pragma.foto.Service.infrastructure.persistence.repository;

import com.pragma.foto.Service.domain.dto.FotoDto;
import com.pragma.foto.Service.domain.repository.FotoRepositoryInterface;
import com.pragma.foto.Service.infrastructure.persistence.crud.FotoCrudInterface;
import com.pragma.foto.Service.infrastructure.persistence.entity.FotoEntity;
import com.pragma.foto.Service.infrastructure.persistence.mapper.FotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FotoRepository implements FotoRepositoryInterface {

    @Autowired
    private FotoMapper fotoMapper;

    @Autowired
    private FotoCrudInterface fotoCrudInterface;

    @Override
    public Optional<FotoDto> findByCustomerId(Long customerId) {
        //return Optional.empty();
        Optional<FotoEntity> fotoEntity = fotoCrudInterface.findByCustomerId(customerId);
        return fotoEntity.map(fot -> fotoMapper.entityToDomain(fotoEntity.get()));
    }

    @Override
    public void save(FotoDto fotoDto) {
        FotoEntity fotoEntity = fotoMapper.domainToEntity(fotoDto);
        fotoCrudInterface.save(fotoEntity);
    }

    @Override
    public void delete(FotoDto fotoDto) {
        FotoEntity fotoEntity = fotoMapper.domainToEntity(fotoDto);
        fotoCrudInterface.delete(fotoEntity);
    }
}
