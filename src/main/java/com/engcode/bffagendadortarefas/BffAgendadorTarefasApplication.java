package com.engcode.bffagendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Amotação para dizen que está trabalhando com spring boot
@SpringBootApplication
//Anotação para habilitar o FeignClient
@EnableFeignClients

public class BffAgendadorTarefasApplication {

	public static void main(String[] args) {

		SpringApplication.run(BffAgendadorTarefasApplication.class, args);

	}

}
