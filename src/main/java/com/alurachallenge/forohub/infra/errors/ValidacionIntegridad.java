package com.alurachallenge.forohub.infra.errors;

public class ValidacionIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
