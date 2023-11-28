package com.jpersou.userapi.service;

import com.jpersou.userapi.converter.DTOconverter;
import com.jpersou.userapi.model.User;
import com.jpersou.userapi.repository.UserRepository;
import com.jpersou.shoppingclient.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public static User getUser(Integer id, String name, String document) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setDocument(document);
        user.setAddress("Address");
        user.setPhone("5432");
        return user;
    }

    @Test
    public void testListAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(getUser(1,"User Name", "123"));
        users.add(getUser(2,"User Name", "321"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> usersReturn = userService.getAll();
        Assertions.assertEquals(2,usersReturn.size());

    }



    @Test
    public void testUserSave() {
        User userDB = getUser(1,"User Name", "123");

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(userDB));
        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(userDB);

        UserDTO userDTO = DTOconverter.convert(userDB);
        userDTO.setAddress("New Address");
        userDTO.setPhone("9999");

        UserDTO userDTO1 = userService.editUser(1L, userDTO);
        Assertions.assertEquals("New Address", userDTO1.getAddress());
        Assertions.assertEquals("9999", userDTO1.getPhone());
    }

}