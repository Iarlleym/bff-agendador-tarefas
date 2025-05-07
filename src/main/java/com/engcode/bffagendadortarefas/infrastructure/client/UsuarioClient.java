package com.engcode.bffagendadortarefas.infrastructure.client;


import com.engcode.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Coloca o nome e a url que no caso vai ser uma variavél no aplicationProperties
@FeignClient (name = "usuario",  url = "${usuario.url}")
public interface UsuarioClient {

    //Metodo para buscar o usuário por e-mail
    @GetMapping ("/usuario")
    UsuarioDTOResponse buscaUsuarioPorEmail (@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    //Cria o metodo salvaUsuario e usa o metodo pra isso da UsuarioService.
    @PostMapping ("/usuario")
    UsuarioDTOResponse salvaUsuario (@RequestBody UsuarioDTORequest usuarioDTORequest);

    //Metodo para fazer o login e retornar o token gerado.
    @PostMapping ("/usuario/login")
    String login (@RequestBody LoginDTORequest loginDTORequest);

    //Metodo para deletar o usuario por email - Usa Void pq não tem retorno apenas se deu certo ou não.
    @DeleteMapping ("/usuario/{email}")
    void deletaUsuarioPorEmail (@PathVariable String email, @RequestHeader ("Authorization") String token);

    //Metodo para atualizar nome, email e senha
    @PutMapping ("/usuario")
    UsuarioDTOResponse atualizaDadosDoUsuario (@RequestBody UsuarioDTORequest usuarioDTORequest, @RequestHeader ("Authorization") String token);

    //Metodo para atualizar dados do endereço.
    @PutMapping ("/usuario/endereco")
    EnderecoDTOResponse atualizaEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest, @RequestParam ("id") Long id, @RequestHeader ("Authorization") String token);

    //Metodo para atualizar dados do telefone.
    @PutMapping ("/usuario/telefone")
    TelefoneDTOResponse atualizaTelefone (@RequestBody TelefoneDTORequest telefoneDTORequest, @RequestParam ("id") Long id, @RequestHeader ("Authorization") String token);

    //Metodo para adicionar endereço
    @PostMapping ("/usuario/endereco")
    EnderecoDTOResponse cadastraEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest, @RequestHeader ("Authorization") String token);

    //Metodo para adicionar telefone
    @PostMapping ("/usuario/telefone")
    TelefoneDTOResponse cadastraTelefone (@RequestBody TelefoneDTORequest telefoneDTORequest, @RequestHeader ("Authorization") String token);

    //Buscar informações do cep via api externa.
    //Esse cep ele pode ser recebido via parametro ou do jeito que está direto no end point.
    @GetMapping ("/usuario/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep (@PathVariable ("cep") String cep);

}
