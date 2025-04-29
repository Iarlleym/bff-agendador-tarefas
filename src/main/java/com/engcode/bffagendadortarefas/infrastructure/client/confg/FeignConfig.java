package com.engcode.bffagendadortarefas.infrastructure.client.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//O spring boot entende que é uma classe de configuração.
@Configuration
public class FeignConfig {


    @Bean
    public FeignError feignError () {
        return new FeignError();
    }

}
