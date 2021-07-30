package com.pragma.foto.Service.infrastructure.persistence.mapper;

import com.pragma.foto.Service.domain.dto.FotoDto;
import com.pragma.foto.Service.infrastructure.persistence.crud.FotoCrudInterface;
import com.pragma.foto.Service.infrastructure.persistence.entity.FotoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FotoMapper {

    @Autowired
    private FotoCrudInterface fotoCrudInterface;

    public FotoDto entityToDomain(FotoEntity fotoEntity) {
        return FotoDto.builder()
                .clienteId(fotoEntity.getCustomerId())
                .foto(fotoEntity.getFoto())
                .build();
    }

    public FotoEntity domainToEntity(FotoDto fotoDto) {
        Optional<FotoEntity> fotoEntidadOptional = fotoCrudInterface.findByCustomerId(fotoDto.getClienteId());
        if (fotoEntidadOptional.isEmpty()) {
            fotoEntidadOptional = Optional.of(FotoEntity.builder().build());
        }
        fotoEntidadOptional.get().setFoto(fotoDto.getFoto());
        fotoEntidadOptional.get().setCustomerId(fotoDto.getClienteId());
        return fotoEntidadOptional.get();
    }

}
