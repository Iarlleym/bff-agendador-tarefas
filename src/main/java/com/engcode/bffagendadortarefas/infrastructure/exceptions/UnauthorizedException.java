package com.engcode.bffagendadortarefas.infrastructure.exceptions;


//Não Extend o UnauthorizedException pq ele é do spring security e no bff não tem.
public class UnauthorizedException extends RuntimeException {



    public UnauthorizedException(String menssagem) {

        super(menssagem);
    }

    public UnauthorizedException (String menssagem, Throwable throwable) {
        super(menssagem, throwable);
    }
}
