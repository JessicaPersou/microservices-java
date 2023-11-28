package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingclient.dto.UserDTO;
import com.jpersou.shoppingclient.exception.UserNotFoundException;
import com.jpersou.shoppingapi.converter.DTOconverter;
import com.jpersou.shoppingapi.model.User;
import com.jpersou.shoppingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return DTOconverter.convert(user);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setDateRegister(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return  DTOconverter.convert(user);
    }

    public UserDTO delete(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return DTOconverter.convert(user);
    }

    public UserDTO findByDocumentAndKey(String doc, String key){
            User user = userRepository.findByDocumentAndKey(doc, key);
            if(user != null){
                return DTOconverter.convert(user);
            }
            throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name){
        List<User> users = userRepository.queryByNameLike(name);
        return users
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if ((userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail()))){
            user.setEmail(userDTO.getEmail());
        }

        if ((userDTO.getPhone() != null && !user.getPhone().equals(userDTO.getPhone()))){
            user.setPhone(userDTO.getPhone());
        }

        if ((userDTO.getAddress() != null && !user.getAddress().equals(userDTO.getAddress()))){
            user.setAddress(userDTO.getAddress());
        }

        user = userRepository.save(user);
        return DTOconverter.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users
                .map(DTOconverter::convert);
    }
}
