package com.pragma.foto.Service.infrastructure.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "serverfoto")
public class FotoEntity {

    @Id
    private String id;
    @Field
    private Long customerId;
    @Field
    private String foto;

}
