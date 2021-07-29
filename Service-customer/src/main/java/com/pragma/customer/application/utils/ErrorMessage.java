package com.pragma.customer.application.utils;

public class ErrorMessage {

    private static final String IDENTIFICACION_NO_REGISTRADA = "la identificacion %s: %d no esta registrada";
    private static final String IDENTIFICACION_YA_REGISTRADA = "la identificacion %s: %d ya esta registrada";
    private static final String SIN_CLIENTES_POR_EDAD = "no existen clientes con edad mayor o igual a %d";

    public static String identificacionNoRegistrada(String tipo, Integer numero) {
        return String.format(IDENTIFICACION_NO_REGISTRADA, tipo, numero);
    }

    public static String identificacionYaRegistrada(String tipo, Integer numero) {
        return String.format(IDENTIFICACION_YA_REGISTRADA, tipo, numero);
    }

    public static String sinClientesPorEdad(Integer edad) {
        return String.format(SIN_CLIENTES_POR_EDAD, edad);
    }

}
