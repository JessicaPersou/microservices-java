package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingclient.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private String userApiURL = "http://localhost:8080";

    public UserDTO getUserByDocument(String doc){
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(userApiURL)
                    .build();

            Mono<UserDTO> userDTOMono = webClient.get()
                    .uri("/user/" + doc + "/document")
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return userDTOMono.block();

        }catch (Exception e){
            throw new RuntimeException("User Not Found");
        }
    }
}
