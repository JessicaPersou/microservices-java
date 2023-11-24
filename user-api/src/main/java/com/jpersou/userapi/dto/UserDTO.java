package com.jpersou.userapi.dto;

import com.jpersou.userapi.model.User;
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
    private LocalDateTime dateRegister;

    public static UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setDocument(user.getDocument());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setDateRegister(user.getDateRegister());

        return userDTO;
    }
}
