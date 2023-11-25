package com.jpersou.productapi.converter;


import com.jpersou.productapi.model.Category;
import com.jpersou.productapi.model.Product;
import com.jpersou.shoppingclient.dto.CategoryDTO;
import com.jpersou.shoppingclient.dto.ProductDTO;

public class DTOconverter {
    public static CategoryDTO convert(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setProductIdentifier(product.getProductIdentifier());

        if(product.getCategory() != null){
            productDTO.setCategoryDTO(
                    DTOconverter.convert(product.getCategory()));
        }

        return productDTO;
    }
}
