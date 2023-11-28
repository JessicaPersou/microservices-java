package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingapi.converter.DTOconverter;
import com.jpersou.shoppingapi.model.User;
import com.jpersou.shoppingapi.repository.UserRepository;
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

    @Test
    public void testListAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(getUser(1, "User Name", "123"));
        users.add(getUser(2, "User Name", "123"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> usersReturn = userService.getAll();
        Assertions.assertEquals(2, usersReturn.size());
    }

    @Test
    public void testSaveUser() {
        User userDB = getUser(1, "User Name", "123");
        UserDTO userDTO = DTOconverter.convert(userDB);

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(userDB);

        UserDTO user = userService.save(userDTO);
        Assertions.assertEquals("User Name", user.getName());
        Assertions.assertEquals("123", user.getDocument());
    }

    @Test
    public void testEditUser() {
        User userDB = getUser(1, "User Name", "123");

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(userDB));
        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(userDB);


        UserDTO userDTO = DTOconverter.convert(userDB);
        userDTO.setAddress("New Address");
        userDTO.setPhone("1234");

        UserDTO user = userService.editUser(1L, userDTO);
        Assertions.assertEquals("New Address", user.getAddress());
        Assertions.assertEquals("1234", user.getPhone());
    }

    public static User getUser(Integer id, String name, String document) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setDocument(document);
        user.setAddress("New Address");
        user.setPhone("5432");
        return user;
    }

}