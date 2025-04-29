package com.engcode.bffagendadortarefas.business.dto.out;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTOResponse {

    private String id;
    private String numero;
    private String ddd;

}
