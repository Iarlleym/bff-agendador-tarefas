package com.engcode.bffagendadortarefas.business;

import com.engcode.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.engcode.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    //metodo de login
    public String loginUsuario (LoginDTORequest loginDTORequest) {
        return  usuarioClient.login(loginDTORequest);
    }

    //metodo que salva usuario.
    public UsuarioDTOResponse salvaUsuario (UsuarioDTORequest usuarioDTORequest) {
        return usuarioClient.salvaUsuario(usuarioDTORequest);
    }

   //metodo para buscarUsuarioPorEmail na entity e converte para UsuarioDTO.
    public UsuarioDTOResponse buscarUsuarioPorEmail (String email, String token) {
        return  usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    //metodo para deletarUsuarioPorEmail
    public void deletaUsuarioPorEmail (String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    //metodo para atualizarDadosDoUsuario sem email e sem telefone.
    public UsuarioDTOResponse atualizaDaddosUsuario (String token, UsuarioDTORequest usuarioDTORequest) {
        return usuarioClient.atualizaDadosDoUsuario(usuarioDTORequest, token);
    }

    //Metodo para atualizar os dados de endereço
    public EnderecoDTOResponse atualizaEndereco (Long idEndereco, EnderecoDTORequest enderecoDTORequest, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTORequest, idEndereco, token);
    }

    //Metodo para atualizar os dados do telefone
    public TelefoneDTOResponse atualizaTelefone (Long idTelefone, TelefoneDTORequest telefoneDTORequest, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTORequest, idTelefone, token);
    }

    //Metodo para cadastro de novos endereços.
    public EnderecoDTOResponse cadastraEndereco (String token, EnderecoDTORequest enderecoDTORequest) {
        return usuarioClient.cadastraEndereco(enderecoDTORequest, token);
    }

    //Metodo para cadastro de novos telefones.
    public TelefoneDTOResponse cadastraTelefone (String token, TelefoneDTORequest telefoneDTORequest) {
        return usuarioClient.cadastraTelefone(telefoneDTORequest, token);
    }

    //Metodo para busca de cep via cep.
    public ViaCepDTOResponse buscarEnderecoPorCep (String cep) {
        return usuarioClient.buscarDadosCep(cep);
    }


}
