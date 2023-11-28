package com.jpersou.shoppingapi.controller;

import com.jpersou.shoppingapi.converter.DTOconverter;
import com.jpersou.shoppingapi.service.UserService;
import com.jpersou.shoppingapi.service.UserServiceTest;
import com.jpersou.shoppingclient.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testListUsers() throws Exception {
        List<UserDTO> users = new ArrayList<>();
        users.add(DTOconverter
                .convert(UserServiceTest.getUser(1, "User Name", "123")));

        Mockito.when(userService.getAll()).thenReturn(users);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals("[{\"name\":\"User Name\"," +
                        "\"document\":\"123\",\"address\":\"New Address\",\"phone\":\"5432\",\"email\":null,\"key\":null,\"dateRegister\":null}]"
                , resp);
    }


}