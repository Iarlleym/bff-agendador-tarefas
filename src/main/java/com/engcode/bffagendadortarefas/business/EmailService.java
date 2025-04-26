package com.engcode.bffagendadortarefas.business;


import com.engcode.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.engcode.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class EmailService {

    //Injetar a dependência de tarefas client.
    private final EmailClient emailClient;

    //Cria os métodos de enviar e-mail de notificação.
    public void enviaEmail (TarefasDTOResponse tarefasDTOResponse) {
        emailClient.enviarEmail(tarefasDTOResponse);
    }


}
