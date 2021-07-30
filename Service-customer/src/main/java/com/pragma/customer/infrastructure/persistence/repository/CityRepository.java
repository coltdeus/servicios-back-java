package com.pragma.customer.infrastructure.persistence.repository;

import com.pragma.customer.domain.dto.CityDto;
import com.pragma.customer.domain.repository.CityRepositoryInterface;
import com.pragma.customer.infrastructure.persistence.crud.CityCrudRepository;
import com.pragma.customer.infrastructure.persistence.entity.CityEntity;
import com.pragma.customer.infrastructure.persistence.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CityRepository implements CityRepositoryInterface {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CityCrudRepository cityCrudRepository;

    @Override
    public Optional<CityDto> findName(String name) {
        Optional<CityEntity> cityEntity = cityCrudRepository.findByName(name);
        return cityEntity.map(city -> cityMapper.toCityDto(city));
        //return Optional.empty();
    }

    @Override
    public void save(CityDto cityDto) {
        CityEntity cityEntity = cityMapper.toCityEntity(cityDto);
        cityCrudRepository.save(cityEntity);
    }

    public Optional<CityEntity> renEntity(String name){
        Optional<CityEntity> cityEntity = cityCrudRepository.findByName(name);
        return cityEntity;
    }

}
