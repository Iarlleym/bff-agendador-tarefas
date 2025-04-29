package com.engcode.bffagendadortarefas.infrastructure.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String menssagem) {

        super(menssagem);
    }

    public BusinessException (String menssagem, Throwable throwable) {
        super(menssagem, throwable);
    }
}
