package com.pragma.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FotoDto {

    private Long customerId;
    private String foto;

    public interface Atributos {
        String CLIENTE_ID = "clienteId";
        String FOTO = "foto";
    }
}
