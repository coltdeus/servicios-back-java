package com.pragma.foto.Service.domain.repository;

import com.pragma.foto.Service.domain.dto.FotoDto;
import java.util.Optional;

public interface FotoRepositoryInterface {

    Optional<FotoDto> findByCustomerId(Long customerId);

    void save(FotoDto fotoDto);

    void delete(FotoDto fotoDto);

}
