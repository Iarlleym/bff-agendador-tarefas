package com.engcode.bffagendadortarefas.infrastructure.client;



import com.engcode.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.engcode.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//Coloca o nome e a url que no caso vai ser uma variavél no aplicationProperties
@FeignClient (name = "agendador-tarefas",  url = "${agendador-tarefas.url}")
public interface TarefasClient {


    //Cria os verbos HTTP
    @PostMapping("/tarefas")
    TarefasDTOResponse gravarTarefas (@RequestBody TarefasDTORequest tarefasDTORequest, @RequestHeader ("Authorization") String token);

    //Get para buscar Lista de tarefas num determinado tempo.
    @GetMapping ("/tarefas/eventos")
   List<TarefasDTOResponse> buscarListaDeTarefasPorPeriodo (
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader ("Authorization") String token);

    //Get para buscar eventos por email.
    @GetMapping("/tarefas")
    List<TarefasDTOResponse> buscaTarefasPorEmail (@RequestHeader ("Authorization") String token);

    //Metodo para deletar as tarefas por Id
    @DeleteMapping("/tarefas")
    void DeletaTarefaPorId (@RequestParam ("id") String id, @RequestHeader ("Authorization") String token);

    //Metodo para alteração de status das tarefas.
    @PatchMapping ("/tarefas")
    TarefasDTOResponse alteraStatusDeNotificacao (@RequestParam ("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                  @RequestParam ("id") String id, @RequestHeader ("Authorization") String token);

    //Metodo para atualizar os dados das tarefas.
    @PutMapping("/tarefas")
    TarefasDTOResponse atualizaTarefas (@RequestBody TarefasDTORequest tarefasDTORequest, @RequestParam ("id") String id,
                                        @RequestHeader ("Authorization") String token);

}
