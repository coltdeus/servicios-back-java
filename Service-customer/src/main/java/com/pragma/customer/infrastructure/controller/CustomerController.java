package com.pragma.customer.infrastructure.controller;

import com.pragma.customer.application.utils.PersonalizedNameCustomer;
import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.domain.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = PersonalizedNameCustomer.REST_CONTROLLER)
@Api(tags = { "Controlador customer"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//---TODOS(COMPLETO)---
    @GetMapping(PersonalizedNameCustomer.REST_ALL)
    @ApiOperation("consigue todos los customers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no se encontraron clientes registrados")
    })
    public ResponseEntity<List<CustomerDto>> getAll() throws NotFoundException {
        List<CustomerDto> customerDtos = customerService.getAll();
        if(customerDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

//---ENCONTRAR IDENTIFICACION---
    @GetMapping(PersonalizedNameCustomer.REST_GET_IDENTIFICATION)
    @ApiOperation("obtiene un cliente dado su tipo y numero de documento")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<CustomerDto> getIdentification(
            @PathVariable("document") Integer document,
            @PathVariable("type") String type
    ) throws NotFoundException {
        CustomerDto customerDto = customerService.findByIdentification(type, document);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
//---AÑO---
    @GetMapping(PersonalizedNameCustomer.REST_AGE)
    @ApiOperation("obtiene una lista de los clientes cuya edad sea mayor o igual a el parametro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no hay cliente con esa edad")
    })
    public  ResponseEntity<List<CustomerDto>> findByAge(@PathVariable("age") Integer age) throws NotFoundException {
        List<CustomerDto> customerDto = customerService.findByAge(age);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
//---GUARDAR---
    @PostMapping(PersonalizedNameCustomer.REST_SAVE)
    @ApiOperation("guarda un cliente")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<CustomerDto> save (@RequestBody CustomerDto customerDto) throws NotFoundException {
        customerService.save(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//---ACTUALIZAR---
    @PutMapping
    @ApiOperation("actualiza un cliente")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) throws NotFoundException {
        customerService.update(customerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//---ELIMINAR---
    @DeleteMapping(PersonalizedNameCustomer.REST_DELETE)
    @ApiOperation("elimina un cliente")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<CustomerDto> dalete(
            @PathVariable("document") Integer document,
            @PathVariable("type") String type
    ) throws NotFoundException {
        customerService.delete(type, document);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//---ELIMINAR POR CUSTOMER---
    @DeleteMapping(PersonalizedNameCustomer.REST_DELETE_CUSTOMER)
    @ApiOperation("elimina un cliente por el body")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<CustomerDto> daleteCustomer(@RequestBody CustomerDto customerDto) throws NotFoundException {
        customerService.deleteCustomer(customerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    /**

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> listCustomer(){
        List<CustomerEntity> customers = customerService.findAll();
        if (customers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        //return ResponseEntity.ok(customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    */



}
