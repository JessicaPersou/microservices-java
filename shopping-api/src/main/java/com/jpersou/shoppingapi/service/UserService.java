package com.jpersou.userapi.service;

import com.jpersou.shoppingclient.dto.UserDTO;
import com.jpersou.shoppingclient.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private String userApiURL = "http://localhost:8080";

    public UserDTO getUserByDocument(String doc, String key){
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(userApiURL)
                    .build();

            Mono<UserDTO> userDTOMono = webClient.get()
                    .uri("/user/" + doc + "/document?key=" + key)
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return userDTOMono.block();

        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
}
