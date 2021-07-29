package com.pragma.foto.Service.domain.service;

import com.pragma.foto.Service.aplication.utils.ErrorMessage;
import com.pragma.foto.Service.domain.dto.FotoDto;
import com.pragma.foto.Service.domain.repository.FotoRepositoryInterface;
import com.pragma.foto.Service.infrastructure.persistence.mapper.FotoMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    private  FotoRepositoryInterface fotoRepositoryInterface;

    //@Autowired
    //private FotoMapper fotoMapper;

    public FotoDto getCustomerId(Long customerId) throws NotFoundException {
        //return fotoMapper;
        Optional<FotoDto> fotoDto = fotoRepositoryInterface.findByCustomerId(customerId);
        if (fotoDto.isEmpty()){
            throw new NotFoundException(ErrorMessage.customerWithoutPhoto(customerId));
        }
        return fotoDto.get();
    }

    public List<FotoDto> getCustomerIds(List<Long> customerIds) throws NotFoundException {
        List<FotoDto> fotoDtos = new ArrayList<>();
        for (Long customerId : customerIds){
            FotoDto fotoDto = getCustomerId(customerId);
            fotoDtos.add(fotoDto);
        }
        return fotoDtos;
    }

    public void save(FotoDto fotoDto){
        fotoRepositoryInterface.save(fotoDto);
    }

    public void delete(Long customerId){
        Optional<FotoDto> fotoDto = fotoRepositoryInterface.findByCustomerId(customerId);
        fotoRepositoryInterface.delete(fotoDto.get());
    }

}
