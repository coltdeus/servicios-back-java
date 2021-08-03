package com.pragma.customer.infrastructure.rest;

import com.pragma.customer.application.utils.PersonalizedNameFoto;
import com.pragma.customer.domain.dto.FotoDto;
import javassist.NotFoundException;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "servicio-foto", url ="localhost:8182")
//@RequestMapping("/service-foto/api"+PersonalizedNameFoto.REST_CONTROLLER)
public interface FotoRest {
    @GetMapping(PersonalizedNameFoto.REST_GET_CUSTOMER_ID)
    public ResponseEntity<FotoDto> getCustomerId(@PathVariable Long customerId) throws NotFoundException;

    @PostMapping(PersonalizedNameFoto.REST_PUT_CUSTOMERS_FOTO)
    public ResponseEntity<FotoDto> save(@RequestBody FotoDto fotoDto);

    @DeleteMapping(PersonalizedNameFoto.REST_DELETE)
    public ResponseEntity<FotoDto> delete(@PathVariable Long customerId);

    @PostMapping(PersonalizedNameFoto.REST_GET_CUSTOMERS_ID)
    public ResponseEntity<List<FotoDto>> getCustomerIds(@RequestBody List<Long> customerId) throws NotFoundException;
}
