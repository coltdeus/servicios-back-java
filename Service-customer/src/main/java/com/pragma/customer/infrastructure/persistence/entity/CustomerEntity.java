package com.pragma.customer.infrastructure.persistence.entity;

import com.pragma.customer.domain.dto.FotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "El apellido no debe ser vacio")
    private String lastName;
    @Positive(message = "El año debe ser mayor a cero")
    private Integer age;

    //@OneToOne(mappedBy = "customerEntity", fetch = FetchType.LAZY)

    // cascade = CascadeType.ALL
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id", nullable = false, unique = true)
    private IdentificationEntity identificationEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    //@Transient
    //private FotoDto fotoDto;

}
