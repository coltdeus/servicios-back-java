package com.pragma.customer.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "identifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentificationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "el tipo no puede estar vacio")
    private String type;
    @NotEmpty(message = "el documento no puede estar vacio")
    @Positive(message = "la cedula debe ser mayor que cero")
    private Integer number;

    //@JsonIgnore
    //@OneToOne(mappedBy="identificationEntity", cascade = CascadeType.ALL)
    //private CustomerEntity customerEntity;
    //@JoinColumn(name = "customer_id", unique = true)
}
