package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingclient.dto.ProductDTO;
import com.jpersou.shoppingclient.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081}")
    private String productApiURL;

    public ProductDTO getProductByIdentifier(String productIdentifier){
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(productApiURL)
                    .build();

            Mono<ProductDTO> productDTOMono = webClient.get()
                    .uri("/product/" + productIdentifier)
                    .retrieve()
                    .bodyToMono(ProductDTO.class);

            return productDTOMono.block();

        }catch (Exception e){
            throw new ProductNotFoundException();
        }
    }
}
