package com.engcode.bffagendadortarefas.business;


import com.engcode.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.engcode.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.engcode.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {

    //Injetar a dependência de tarefas client.
    private final TarefasClient tarefasClient;

    //Cria os métodos de salvar os dados fazendo a converção.
    public TarefasDTOResponse gravarTarefa (String token, TarefasDTORequest tarefasDTORequest) {
        return tarefasClient.gravarTarefas(tarefasDTORequest, token);
    }

    //metodo para buscar as tarefas em um deternimado periodo de tempo.
    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo (String token, LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefasClient.buscarListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    //metodo para buscar as tarefas por email.
    public List<TarefasDTOResponse> buscaTarefasPorEmail (String token) {
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    //metodo para deletar tarefa por id, não precisa criar nada no TarefasRepository pq o Mongo já tras pronto a função de deletar.
    public void DeletaTarefaPorId (String id, String token) {
        tarefasClient.DeletaTarefaPorId(id, token);
    }

    //metodo para atualizar o status da tarefa
    public TarefasDTOResponse alteraStatus (StatusNotificacaoEnum statusNotificacaoEnum, String id, String token) {
        return tarefasClient.alteraStatusDeNotificacao(statusNotificacaoEnum, id, token);
    }

    //O metodo para atualizar dados das tarefas será feito com o mapstruct
    public TarefasDTOResponse atualizaTarefas (TarefasDTORequest tarefasDTORequest, String id, String token) {
        return tarefasClient.atualizaTarefas(tarefasDTORequest, id, token);
    }

}
