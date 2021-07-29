package com.pragma.customer.domain.service;

import com.pragma.customer.domain.dto.CityDto;
import com.pragma.customer.domain.repository.CityRepositoryInterface;
import com.pragma.customer.infrastructure.persistence.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepositoryInterface cityRepositoryInterface;

    @Autowired
    public CityMapper cityMapper;

    public CityDto save (String name){
        Optional<CityDto> cityDto = cityRepositoryInterface.findName(name);
        if (cityDto.isEmpty()){
            cityDto = Optional.of(CityDto.builder()
                    .name(name)
                    .build());
            cityRepositoryInterface.save(cityDto.get());
            cityDto = cityRepositoryInterface.findName(name);
        }
        return cityDto.get();
    }

    /**

    public CityEntity save(String name){
        Optional<CityEntity> cityEntity = cityRepositoryInterface.findName(name);
        if(cityEntity.isEmpty()){
            cityEntity = Optional.of(CityEntity.builder().name(name).build());
            cityRepositoryInterface.save(cityEntity.get());
            cityEntity = cityRepositoryInterface.findName(name);
        }
        return cityEntity.get();
    }
    */
}
