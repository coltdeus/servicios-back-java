package com.pragma.foto.Service.aplication.utils;

public class ErrorMessage {

    private static final String NOT_FOUND_FOTO = "the customer does not containt a photo with id %d";

    public static String customerWithoutPhoto(Long customerId) {
        return String.format(NOT_FOUND_FOTO, customerId);
    }

}
