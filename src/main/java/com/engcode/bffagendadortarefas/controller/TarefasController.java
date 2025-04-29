package com.engcode.bffagendadortarefas.controller;


import com.engcode.bffagendadortarefas.business.TarefasService;
import com.engcode.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.engcode.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.engcode.bffagendadortarefas.infrastructure.security.SecurityConfig;
import feign.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping ("/tarefas")
@RequiredArgsConstructor
//Anotações para a ducumentação pelo swagger
//@Tag nome e descrição para localização em caso de mais de uma controller.
@Tag(name = "Tarefas", description = "Cadastro de tarefas")
//A documentação swagger não permite passar o token do header, pra contornar temos que usar duas anotações.
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    //Injeta a dependencia do TarefasService
    private final TarefasService tarefasService;


    //Cria os verbos HTTP
    @PostMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Salvar tarefas de usuários", description = "Cria uma nova tarefa.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity <TarefasDTOResponse> gravarTarefas (@RequestBody TarefasDTORequest tarefasDTORequest, @RequestHeader (value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTORequest));
    }

    //Get para buscar Lista de tarefas num determinado tempo.
    @GetMapping ("/eventos")
    @Operation(summary = "Buscar tarefas por periodo.", description = "Buscar tarefas cadastradas por periodo.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Tarefa encontradas com sucesso.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    @ApiResponse (responseCode = "401" , description = "Usuário não autorizado.")
    public  ResponseEntity <List<TarefasDTOResponse>> buscarListaDeTarefasPorPeriodo (
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal
            , @RequestHeader (name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(token, dataInicial, dataFinal));

    }

    //Get para buscar eventos por email.
    @GetMapping
    @Operation(summary = "Buscar lista de tarefas por e-mail de usuário.", description = "Buscar tarefas cadastradas por usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Tarefa encontradas com sucesso.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    @ApiResponse (responseCode = "403" , description = "E-mail não encontrado.")
    @ApiResponse (responseCode = "401" , description = "Usuário não autorizado.")
    public  ResponseEntity <List<TarefasDTOResponse>> buscaTarefasPorEmail (@RequestHeader (name = "Authorization", required = false) String token)  {
        /*
        * Tb pode ser feito assim
        *List<TarefasDTO> tarefasDTOS = tarefasService.buscaTarefasPorEmail(token);
        * return ResponseEntity.ok(tarefasDTOS);
        * */
        //Não está usando exceções por está passando o token e dificilmente terá erro de exceções.

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    //Metodo para deletar as tarefas por Id
    @DeleteMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Deletar tarefas por Id.", description = "Deleta tarefas cadastradas por id.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Tarefas deletadas com sucesso.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    @ApiResponse (responseCode = "403" , description = "Tarefa id não encontrada.")
    @ApiResponse (responseCode = "401" , description = "Usuário não autorizado.")
    public ResponseEntity <Void> DeletaTarefaPorId (@RequestParam ("id") String id, @RequestHeader (name = "Authorization", required = false) String token) {

        tarefasService.DeletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    //Metodo para alteração de status das tarefas.
    @PatchMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Atualizar status de notificação das tarefas.", description = "Altera status de notificação das tarefas cadastradas.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Status alterado com sucesso.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    @ApiResponse (responseCode = "403" , description = "Tarefa id não encontrada.")
    @ApiResponse (responseCode = "401" , description = "Usuário não autorizado.")
    private  ResponseEntity<TarefasDTOResponse> alteraStatusDeNotificacao (@RequestParam ("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                                           @RequestParam ("id") String id
                                                                    , @RequestHeader (name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(statusNotificacaoEnum, id,token));
    }

    //Metodo para atualizar os dados das tarefas.
    @PutMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Altera dados das tarefas.", description = "Altera dados das tarefas cadastradas.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Tarefa alterada com sucesso.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    @ApiResponse (responseCode = "403" , description = "Tarefa id não encontrada.")
    @ApiResponse (responseCode = "401" , description = "Usuário não autorizado.")
    public ResponseEntity<TarefasDTOResponse> atualizaTarefas (@RequestBody TarefasDTORequest tarefasDTORequest, @RequestParam ("id") String id, @RequestHeader (name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.atualizaTarefas(tarefasDTORequest, id, token));
    }


}
