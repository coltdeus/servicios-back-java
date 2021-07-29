package com.pragma.foto.Service.infrastructure.controller;

import com.pragma.foto.Service.aplication.utils.PersonalizedName;
import com.pragma.foto.Service.domain.dto.FotoDto;
import com.pragma.foto.Service.domain.service.FotoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PersonalizedName.REST_CONTROLLER)
public class FotoController {

    @Autowired
    private FotoService fotoService;

    @GetMapping(PersonalizedName.REST_GET_CUSTOMER_ID)
    public ResponseEntity<FotoDto> getCustomerId(@PathVariable Long customerId) throws NotFoundException {
        return new ResponseEntity<>(fotoService.getCustomerId(customerId), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<FotoDto> save(@RequestBody FotoDto fotoDto){
        fotoService.save(fotoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(PersonalizedName.REST_DELETE)
    public ResponseEntity<FotoDto> delete(@PathVariable Long customerId){
        fotoService.delete(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(PersonalizedName.REST_GET_CUSTOMERS_ID)
    public ResponseEntity<List<FotoDto>> getCustomerIds(@RequestBody List<Long> customerId) throws NotFoundException {
        return new ResponseEntity<>(fotoService.getCustomerIds(customerId), HttpStatus.OK);
    }

}
