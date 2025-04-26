package com.engcode.bffagendadortarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {

    private String id;
    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

}
