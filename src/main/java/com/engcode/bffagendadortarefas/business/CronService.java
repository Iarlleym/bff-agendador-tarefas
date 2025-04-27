package com.engcode.bffagendadortarefas.business;

import com.engcode.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.engcode.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
//Colocar Log para conseguir validar oq está acontecendo com o código.
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    //Para metodos estáticos
    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    //essa anitação é para o cron utilizando as varáveis setadas no application.
    //Tem que olhar o site para fazer rodar no tem que vc necessita.
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasDaProximaHora () {

        String token = login(coverterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");

        // Chama a hora atual e o plusHours(1) chama com uma hora a frente.
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        //Dá 5 min de intervalo
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        //Qualquer usuário cadastrado está apto a esta nessa lista de tarefas.
        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(token, horaFutura, horaFuturaMaisCinco);
        log.info("Tarefas encontradas" + listaDeTarefas);
        listaDeTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("E-mail enviado para o usuario" +tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);});
        log.info("Finalizada a busca e notificação de tarefas.");

    }

    //Metodo só para fazer o login e retorna o token
    public String login (LoginDTORequest loginDTORequest) {
        return usuarioService.loginUsuario(loginDTORequest);
    }

    //comverter o login para dto
    public LoginDTORequest coverterParaRequestDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }


}
