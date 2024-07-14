package com.alurachallenge.forohub.infra.errors;

public class ValidacionIntegridad extends RuntimeException{
    public ValidacionIntegridad(String s) {
        super(s);
    }
}
