package com.engcode.bffagendadortarefas.business.dto.in;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    private String numero;
    private String ddd;

}
