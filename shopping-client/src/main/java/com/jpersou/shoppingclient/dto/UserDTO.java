package com.jpersou.shoppingclient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Nome é Obrigatório")
    private String name;
    @NotBlank(message = "Documento é Obrigatório")
    private String document;
    private String address;
    private String phone;
    @NotBlank(message = "E-mail é Obrigatório")
    private String email;
    private String key;
    private LocalDateTime dateRegister;


}
