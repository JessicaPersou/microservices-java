package com.jpersou.userapi.model;


import com.jpersou.shoppingclient.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    public static User convert(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setDocument(userDTO.getDocument());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setKey(userDTO.getKey());
        user.setDateRegister(userDTO.getDateRegister());

        return user;
    }
}
