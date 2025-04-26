package com.engcode.bffagendadortarefas.controller;


import com.engcode.bffagendadortarefas.business.UsuarioService;
import com.engcode.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.engcode.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.engcode.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.engcode.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario") //coloca qual uri para o banco de dados.
@RequiredArgsConstructor
//Anotações para a ducumentação pelo swagger
//@Tag nome e descrição para localização em caso de mais de uma controller.
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    //Cria a dependencia para a classe UsarioService, para usar os metodos que tem nela.
    private final UsuarioService usuarioService;

    //Cria o metodo salvaUsuario e usa o metodo pra isso da UsuarioService.
    @PostMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Salvar usuário", description = "Cria e salva um novo usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Usuário salvo com sucesso.")
    @ApiResponse (responseCode = "400", description = "Usuário já cadastrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario (@RequestBody UsuarioDTORequest usuarioDTORequest){
       return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Login de usuário", description = "Login do usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Usuário logado com sucesso.")
    @ApiResponse (responseCode = "401", description = "Credencias Inválidas.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public String login (@RequestBody LoginDTORequest loginDTORequest){
        //Os dados vão ser passados lá do microserviço usuario.
        return usuarioService.loginUsuario(loginDTORequest);
    }

    //Metodo para buscar o usuário por e-mail
    @GetMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Buscar dados de usuário por e-mail.", description = "Buscar dados do usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Usuário encontrado.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail (@RequestParam ("email") String email, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    //Metodo para deletar o usuario por email - Usa Void pq não tem retorno apenas se deu certo ou não.
    @DeleteMapping ("/{email}")
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Deletar usuário por e-mail.", description = "Deleta usuário por e-mail.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Usuário deletado com sucesso.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity <Void> deletaUsuarioPorEmail (@PathVariable String email, @RequestHeader ("Authorization") String token) { //OBS o nome dentro das chaves {/email} tem que ser igual ao nome da variável.
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    //Metodo para atualizar nome, email e senha
    @PutMapping
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Atualizar dados de usuário.", description = "Atualiza dados de usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Usuário atualizado com sucesso.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse>  atualizaDadosDoUsuario (@RequestBody UsuarioDTORequest usuarioDTORequest, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaDaddosUsuario(token, usuarioDTORequest));
    }

    //Metodo para atualizar dados do endereço.
    @PutMapping ("/endereco")
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Atualizar endereço do usuário.", description = "Atualiza dados de endereço do usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Endereço atualizado com sucesso.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public  ResponseEntity<EnderecoDTOResponse> atualizaEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest, @RequestParam ("id") Long id, @RequestHeader ("Authorization") String token) {
        return  ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTORequest, token));
    }

    //Metodo para atualizar dados do telefone.
    @PutMapping ("/telefone")
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Atualizar telefone do usuário.", description = "Atualiza dados de telefone do usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Telefone atualizado com sucesso.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public  ResponseEntity<TelefoneDTOResponse> atualizaTelefone (@RequestBody TelefoneDTORequest telefoneDTORequest, @RequestParam ("id") Long id, @RequestHeader ("Authorization") String token) {
        return  ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTORequest, token));
    }

    //Metodo para adicionar endereço
    @PostMapping ("/endereco")
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Adiciona endereço ao usuário.", description = "Adiciona um novo endereço ao usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Endereço adicionado com sucesso.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest, @RequestHeader ("Authorization") String token) {
        return  ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTORequest));
    }

    //Metodo para adicionar telefone
    @PostMapping ("/telefone")
    //Anotação do swagger da uma descrição do metodo.
    @Operation(summary = "Adiciona telefone ao usuário.", description = "Adiciona um novo telefone ao usuário.")
    //Anotação responsalvel pelos status code dos verbos html.
    @ApiResponse (responseCode = "200", description = "Telefone adicionado com sucesso.")
    @ApiResponse (responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse (responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone (@RequestBody TelefoneDTORequest telefoneDTORequest, @RequestHeader ("Authorization") String token) {
        return  ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTORequest));
    }

}
