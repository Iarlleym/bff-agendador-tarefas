package com.engcode.bffagendadortarefas.infrastructure.client;

import com.engcode.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

//Coloca o nome e a url que no caso vai ser uma variavél no aplicationProperties
@FeignClient(name = "notificacao",  url = "${notificacao.url}")
public interface EmailClient {

    //Precisamos passar o e-mail e o e-mail só está no TarefasDTOResponse
    void enviarEmail (@RequestBody TarefasDTOResponse tarefasDTOResponse);

}
