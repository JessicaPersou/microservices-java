package com.jpersou.userapi.service;

import com.jpersou.userapi.dto.UserDTO;
import com.jpersou.userapi.model.User;
import com.jpersou.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return UserDTO.convert(user);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setDateRegister(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return  UserDTO.convert(user);
    }

    public UserDTO delete(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return UserDTO.convert(user);
    }

    public UserDTO findByDocument(String doc){
        User user = userRepository.findByDocument(doc);
        if(user != null){
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::convert)
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
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users
                .map(UserDTO::convert);
    }
}
