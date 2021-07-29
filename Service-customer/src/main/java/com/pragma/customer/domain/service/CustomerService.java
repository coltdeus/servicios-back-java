package com.pragma.customer.domain.service;

import com.pragma.customer.application.utils.ErrorMessage;
import com.pragma.customer.domain.dto.CityDto;
import com.pragma.customer.domain.dto.CustomerDto;
import com.pragma.customer.domain.dto.IdentificationDto;
import com.pragma.customer.domain.repository.CustomerRepositoryInterface;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepositoryInterface customerRepositoryInterface;

    @Autowired
    private CityService cityService;

    @Autowired
    private IdentificationService identificationService;

    private CustomerDto getCustomer(CustomerDto customerDto){
        Optional<CustomerDto> customerId = customerRepositoryInterface
                .getCustomerId(customerDto.getId());
        return customerId.get();
        //return null;
    }

    public List<CustomerDto> getAll(){
        return customerRepositoryInterface.getAll();
    }

    public int findIdIdentificationForCustomer(Long id){
        int idReturn = Math.toIntExact(id);
        return idReturn;
    }

    public CustomerDto findByIdentification(String type, Integer number) throws NotFoundException {
        IdentificationDto identificationDto = identificationService.findByNumberAndType(number, type);
        /**
        List<CustomerDto> customers = getAll();
        CustomerDto myCustomer = customers
                .get(findIdIdentificationForCustomer(identificationDto.getId()));


        CustomerDto customerSave = CustomerDto.builder()
                .identificationDto(identificationDto)
                .build();
         */
        //Optional<CustomerDto> myCustomer = customerRepositoryInterface.getCustomerId(identificationDto.getId());
        Optional<CustomerDto> myCustomer = customerRepositoryInterface.customerIdentification(identificationDto.getId());
        return myCustomer.get();
    }

    public List<CustomerDto> findByAge(Integer age) throws NotFoundException {
        Optional<List<CustomerDto>> customerDtos = customerRepositoryInterface.ageGreaterThanOrEqual(age);
        if (customerDtos.isEmpty()){
            throw new NotFoundException(ErrorMessage.sinClientesPorEdad(age));
        }
        return customerDtos.get();
    }

    public void save(CustomerDto customerDto) throws NotFoundException {
        if(identificationService.exist(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument()
        )){
            //throw new IllegalArgumentException("El Customer ya se encuentra creado(SAVE)");
            throw new IllegalArgumentException(ErrorMessage.identificacionYaRegistrada(
                    customerDto.getIdentificationDto().getType(),
                    customerDto.getIdentificationDto().getDocument())
            );
        }
        CityDto cityDto = cityService.save(customerDto.getCityDto().getName());
        identificationService.save(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument()
        );
        IdentificationDto identificationDto = identificationService.findByNumberAndType(
                customerDto.getIdentificationDto().getDocument(),
                customerDto.getIdentificationDto().getType()
        );
        CustomerDto customerSave = CustomerDto.builder()
                .age(customerDto.getAge())
                .lastName(customerDto.getLastName())
                .name(customerDto.getName())
                .cityDto(cityDto)
                .identificationDto(identificationDto)
                .build();
        customerRepositoryInterface.save(customerSave);
    }

    public void update(CustomerDto customerDto) throws NotFoundException {
        if(!identificationService.exist(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument())
        ){
            //throw new NotFoundException("El cliente no se encuentra registrado(UPDATE)");
            throw new NotFoundException(ErrorMessage.identificacionNoRegistrada(
                    customerDto.getIdentificationDto().getType(),
                    customerDto.getIdentificationDto().getDocument())
            );
        }
        CityDto cityDto = cityService.save(customerDto.getCityDto().getName());
        IdentificationDto identificationDto = identificationService.findByNumberAndType(
                customerDto.getIdentificationDto().getDocument(),
                customerDto.getIdentificationDto().getType()
        );
        CustomerDto customerUpdate = findByIdentification(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument()
        );
        customerUpdate.setAge(customerDto.getAge());
        customerUpdate.setLastName(customerDto.getLastName());
        customerUpdate.setName(customerDto.getName());
        customerUpdate.setCityDto(cityDto);
        customerUpdate.setIdentificationDto(identificationDto);

        customerRepositoryInterface.save(customerUpdate);
    }

    public void deleteCustomer(CustomerDto customerDto) throws NotFoundException {
        if(!identificationService.exist(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument())
        ){
            throw new IllegalArgumentException("El Customer no se encuentra creado(DELETE-CUSTOMER)");
        }
        IdentificationDto identificationDto = identificationService.findByNumberAndType(
                customerDto.getIdentificationDto().getDocument(),
                customerDto.getIdentificationDto().getType()
        );
        CustomerDto customerDelete = findByIdentification(
                customerDto.getIdentificationDto().getType(),
                customerDto.getIdentificationDto().getDocument()
        );
        identificationService.delete(identificationDto);
        customerRepositoryInterface.delete(customerDelete);
    }

    public void delete(String type, Integer document) throws NotFoundException {
        if (!identificationService.exist(type, document)) {
            throw new IllegalArgumentException("El Customer no se encuentra creado(DELETE)");
        }
        IdentificationDto identificationDto = identificationService.findByNumberAndType(
                document, type
        );

        CustomerDto customerDelete = findByIdentification(
                type,
                document
        );
        customerRepositoryInterface.delete(customerDelete);
        identificationService.delete(identificationDto);
        //customerRepositoryInterface.delete(customerDelete);

    }


    /**
    private CustomerEntity getCustomer(CustomerEntity customerEntity){
        Optional<CustomerEntity> customerEntityOptional = customerRepositoryInterface.getCustomerId(customerEntity.getId());
        return customerEntityOptional.get();
        //return null;
    }

    private List<CustomerEntity> getCustomers(List<CustomerEntity> customerEntityList){
        //List<CustomerEntity> customers = new ArrayList<>();
        return customerRepositoryInterface.getAll();
        //return null;
    }

    public CustomerEntity findByIdentification(String type, Integer number) throws NotFoundException {
        IdentificationEntity identificationEntity = identificationService.findByNumberAndType(number, type);
        CustomerEntity customerEntity =identificationEntity.getCustomerEntity();
        return customerEntity;
    }

    public List<CustomerEntity> findByAge(Integer age) throws NotFoundException {
        Optional<List<CustomerEntity>> customerEntities = customerRepositoryInterface.ageGreaterThanOrEqual(age);
        if (customerEntities.isEmpty()){
            throw new NotFoundException("No se encontro respuesta para esa edad(FINDAGE)");
        }
        return customerEntities.get();
    }

    public List<CustomerEntity> findAll(){
        List<CustomerEntity> customerEntities = customerRepositoryInterface.getAll();
        return customerEntities;
    }

    public void save(CustomerEntity customerEntity) throws NotFoundException {
        if(identificationService.exist(customerEntity.getIdentificationEntity().getType(), customerEntity.getIdentificationEntity().getNumber())){
            throw new IllegalArgumentException("El Customer ya se encuentra creado(SAVE)");
        }
        CityEntity cityEntity = cityService.save(customerEntity.getCityEntity().getName());
        identificationService.save(
                customerEntity,
                customerEntity.getIdentificationEntity().getType(),
                customerEntity.getIdentificationEntity().getNumber()
        );
        IdentificationEntity identificationEntity = identificationService.findByNumberAndType(customerEntity.getIdentificationEntity().getNumber(), customerEntity.getIdentificationEntity().getType());
        CustomerEntity customerSave = CustomerEntity.builder()
                .age(customerEntity.getAge())
                .lastName(customerEntity.getLastName())
                .name(customerEntity.getName())
                .cityEntity(cityEntity)
                .identificationEntity(identificationEntity)
                .build();
        customerRepositoryInterface.save(customerSave);
    }

    public void update(CustomerEntity customerEntity) throws NotFoundException {
        if(!identificationService.exist(customerEntity.getIdentificationEntity().getType(), customerEntity.getIdentificationEntity().getNumber())){
            throw new NotFoundException("El cliente no se encuentra registrado(UPDATE)");
        }
        CityEntity cityEntity = cityService.save(customerEntity.getCityEntity().getName());
        IdentificationEntity identificationEntity = identificationService.findByNumberAndType(
                customerEntity.getIdentificationEntity().getNumber(), customerEntity.getIdentificationEntity().getType()
        );
        CustomerEntity customerUpdate = identificationEntity.getCustomerEntity();
        customerUpdate.setAge(customerEntity.getAge());
        customerUpdate.setLastName(customerEntity.getLastName());
        customerUpdate.setName(customerEntity.getName());
        customerUpdate.setCityEntity(customerEntity.getCityEntity());
        customerRepositoryInterface.save(customerUpdate);
    }

    public void deleteCustomer(CustomerEntity customerEntity) throws NotFoundException {
        if(!identificationService.exist(customerEntity.getIdentificationEntity().getType(), customerEntity.getIdentificationEntity().getNumber())){
            throw new IllegalArgumentException("El Customer no se encuentra creado(DELETE)");
        }
        IdentificationEntity identificationEntity = identificationService.findByNumberAndType(
                customerEntity.getIdentificationEntity().getNumber(), customerEntity.getIdentificationEntity().getType()
        );
        CustomerEntity customerDelete = identificationEntity.getCustomerEntity();
        identificationService.delete(identificationEntity);
        customerRepositoryInterface.delete(customerDelete);

    }

    public void delete(String type, Integer number) throws NotFoundException {
        if(!identificationService.exist(type, number)){
            throw new IllegalArgumentException("El Customer no se encuentra creado(DELETE)");
        }
        IdentificationEntity identificationEntity = identificationService.findByNumberAndType(number, type);
        CustomerEntity customerDelete = identificationEntity.getCustomerEntity();
        customerRepositoryInterface.delete(customerDelete);
        identificationService.delete(identificationEntity);

    }

    */
}
