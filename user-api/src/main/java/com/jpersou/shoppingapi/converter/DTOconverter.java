package com.jpersou.shoppingapi.converter;


import com.jpersou.shoppingapi.model.User;
import com.jpersou.shoppingclient.dto.UserDTO;

public class DTOconverter {
    public static UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setDocument(user.getDocument());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setKey(user.getKey());
        userDTO.setDateRegister(user.getDateRegister());

        return userDTO;
    }

}
