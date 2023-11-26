package com.jpersou.shoppingapi.controller;

import com.jpersou.shoppingclient.exception.UserNotFoundException;
import com.jpersou.shoppingapi.service.UserService;
import com.jpersou.shoppingclient.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/{document}/document")
    public UserDTO getUsersByDocumentAndKey(@PathVariable String document,
                                      @RequestParam(name="key") String key){
        return userService.findByDocumentAndKey(document, key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insert(@RequestBody @Valid UserDTO userDTO){
        return userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name = "name", required = true) String name){
        return userService.queryByName(name);
    }

    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id,  @RequestBody UserDTO userDTO){
        return userService.editUser(id, userDTO);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable){
        return userService.getAllPage(pageable);
    }
}
