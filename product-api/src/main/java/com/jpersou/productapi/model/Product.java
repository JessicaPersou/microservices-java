package com.jpersou.productapi.model;

import com.jpersou.productapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Float price;
    private String description;
    private String productIdentifier;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Product convert(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setProductIdentifier(productDTO.getProductIdentifier());

        if(productDTO.getCategoryDTO() != null){
            product.setCategory(Category.convert(productDTO.getCategoryDTO()));
        }
        return product;
    }
}
