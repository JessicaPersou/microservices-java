package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingclient.dto.UserDTO;
import com.jpersou.shoppingclient.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080}")
    private String userApiURL;

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
